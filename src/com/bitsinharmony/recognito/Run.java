package com.bitsinharmony.recognito;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Run {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public static String trainingFolderPath ="E:\\WorkSpace\\SleepGuard\\training_set_2";
	public static String testFolderPath ="E:\\WorkSpace\\SleepGuard\\test_set_2";
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
		// Create a new Recognito instance defining the audio sample rate to be used
		Recognito<String> recognito = new Recognito<>(44100.0f);
		

		File folder_test = new File(testFolderPath);
		File folder_training = new File(trainingFolderPath);
		File[] listOfFiles_test = folder_test.listFiles();
		File[] listOfFiles_training = folder_training.listFiles();
		for (int i = 0; i < listOfFiles_training.length; i++) {

			if (listOfFiles_training[i].isFile()) {

				File f = new File( trainingFolderPath+"\\"+listOfFiles_training[i].getName()); 
				recognito.createVoicePrint(listOfFiles_training[i].getName(), f);
				System.out.println("Trained: "+listOfFiles_training[i].getName());
			}
		}
	
		System.out.println("Universal model trained!!");
		String testFileName = null;
		/*recognito.mergeVoiceSample("Zohaib", new File("E:\\WorkSpace\\SleepGuard\\zohaib2.wav"));
		recognito.mergeVoiceSample("Zohaib", new File("E:\\WorkSpace\\SleepGuard\\test.wav"));
		recognito.mergeVoiceSample("Zohaib", new File("E:\\WorkSpace\\SleepGuard\\zohaib4.wav"));
		recognito.createVoicePrint("Suleman", new File("E:\\WorkSpace\\SleepGuard\\suleman1.wav"));
		recognito.mergeVoiceSample("Suleman", new File("E:\\WorkSpace\\SleepGuard\\suleman2.wav"));*/
		

		// handle persistence the way you want, e.g.:
		// myUser.setVocalPrint(print);
		// userDao.saveOrUpdate(myUser);

		// Now check if the King is back
		
		/*
		while (testFileName==null || !testFileName.equalsIgnoreCase("q")){
			Scanner scan = new Scanner(System.in);
			System.out.print("Please Enter the name of file to be tested: ");
			testFileName = scan.nextLine();
			System.out.println("E:\\Downloads\\Torrents\\ted_sessions\\Session\\test\\wav\\"+testFileName);
			List<MatchResult<String>> matches = recognito.identify(new File("E:\\Downloads\\Torrents\\ted_sessions\\Session\\tests\\wav\\"+testFileName));
			MatchResult<String> match = matches.get(0);
			System.out.println("Matched to: "+match.getKey()+ ",\tLikelihood: " + match.getLikelihoodRatio() + "%");
		}
		*/
		System.out.println("Test,Matched To,Likelihood");
		for (int i = 0; i < listOfFiles_test.length; i++) {

			if (listOfFiles_test[i].isFile()) {

				File f = new File(testFolderPath+"\\"+listOfFiles_test[i].getName()); 
				List<MatchResult<String>> matches = recognito.identify(f);
				MatchResult<String> match = matches.get(0);
				System.out.println(listOfFiles_test[i].getName()+","+match.getKey()+ "," + match.getLikelihoodRatio() + "%");
			}
		}
		
		System.out.println("Done!");
		
//		List<MatchResult<String>> matches = recognito.identify(new File("E:\\Downloads\\Torrents\\ted_sessions\\Session\\test\\wav\\AdoraSvitak.wav"));
	//	MatchResult<String> match = matches.get(0);

		/*if(match.getKey().equals("Zohaib")) {
		    System.out.println("ZOhaib!!! " + match.getLikelihoodRatio() + "% positive about it...");
		}*/
		//System.out.println("Matched to: "+match.getKey()+ ",\tLikelihood: " + match.getLikelihoodRatio() + "%");
	}	

}
