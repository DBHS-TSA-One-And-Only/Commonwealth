package commonwealth.sentencemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Tokenizer {
	
	static String sentence = "", space = " ", punctuation = "";
	static ArrayList<String> brokenSentence = new ArrayList<>(), characterType = new ArrayList<>(); //characterType cotains labels for corresponding 
	static MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
	static String[][] key = new String[40][2];
	
	public static ArrayList<ArrayList<String>> splitString(String input) {
		sentence = input;
		

		for (String s : sentence.split("[@ _ \\t\r]")) {       /*
									* splits sentence into single words at spaces (not punctuations anymore QQ)
									* 
									* Note: will also bring punctuation with word. ie "Hi, I'm Akali" will split into
									* "Hi,", "I'm" , and "Akali"
									*/

			if (!(s.equals(""))) {

				punctuation = s.substring(s.length() - 1);
				if (punctuation.matches("[, ; : . ? !  ( )  [ ] \" \']")) { // checks last character
																			// s for punctuation

					s = s.substring(0, s.length() - 1);
				} else {

					punctuation = "";
				}

				brokenSentence.add(s);
				characterType.add("Word");

				if (!(punctuation.equals(""))) {

					brokenSentence.add(punctuation);
					characterType.add("Punctuation");
				}

				brokenSentence.add(space);
				characterType.add("space");
				punctuation = "";

			}

		}
		Tokenizer.tagger();
		Tokenizer.setup();
		Tokenizer.translate();
		ArrayList<ArrayList<String>> tokenizedString = new ArrayList<>();
		tokenizedString.add(brokenSentence);
		tokenizedString.add(characterType);
		
		return tokenizedString;

		

		// for testing
		/*
		 * for(String b : brokenSentence) { System.out.println(b); }
		 * 
		 * for(String c: characterType){ System.out.println(c); }
		 */

	}
	public static void tagger() {
		for (int i = 0; i < brokenSentence.size(); i++) {

			brokenSentence.set(i, tagger.tagString(brokenSentence.get(i)));

			// System.out.println(tagged); //for testing
		}
	}

	public static void setup() {
		int indexOfIdentifier;
		for (int i = 0; i < brokenSentence.size(); i++) {                                                 // sets up charIdentifier for translating by removing
														  // underscore and adding tag to charIdentifier ArrayList

			if ((indexOfIdentifier = brokenSentence.get(i).indexOf("_")) != -1) {
				characterType.set(i,brokenSentence.get(i).substring(indexOfIdentifier + 1));
				brokenSentence.set(i,brokenSentence.get(i).substring(0, indexOfIdentifier));
			}
		}

		Scanner scanner = null;                         // sets up translator array, first col contains
								// tag, second col contains translated meaning
		try {
			scanner = new Scanner(new File("DBHS-TSA-One-And-Only\\Commonwealth\\postaggerkey"));
		} catch (FileNotFoundException e) {
			// print error msg via gui
		}

		while (scanner.hasNextLine()) {

			int indexRow = 0, indexCol = 0;
			Scanner scanner2 = new Scanner(scanner.nextLine())
					.useDelimiter(",");

			while (scanner2.hasNext()) {
				key[indexRow][indexCol] = scanner2.next();

				if (indexCol == 0) {
					indexCol = 1;
				} else {
					indexCol = 0;
					indexRow++;
				}

			}

			/*
			 * for(int r =0; r < key.length; r ++){ for(int c =0; c < 2; c ++){
			 * System.out.println(key[r][c]); }}
			 * 
			 * for testing
			 */

		}
		
		

	}

	public static void translate() {

		for (int i = 0; i < characterType.size(); i++) {
			for (int j = 0; j < key.length; j++) {
				if (characterType.get(i).equals(key[j][0])) {
					characterType.set(i, key[j][1]);
				}
			}
		}
}
}