package commonwealth.sentencemanager;

import commonwealth.members.Sentence;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tokenizer {
	
	static String sentence = "", space = " ", punctuation = "";
	static ArrayList<String> tokenizedSentence = new ArrayList<>(), charIdentifier = new ArrayList<>(); //charIdentifier cotains labels for corresponding 
	static MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
	static String[][] key = new String[40][2];
	
	public static Sentence splitString(String input) {
		sentence = input;
		

		for (String s : sentence.split("[@ _ \\t\r]")) { /*
														 * splits sentence into single words at spaces (not punctuations anymore QQ)
														 * 
														 * Note: will also bring punctuation with word. ie "Hi, I'm Akali" will split into
														 * "Hi,", "I'm" , and "Akali"
														 */

			if (!(s.equals(""))) {

				punctuation = s.substring(s.length() - 1);
				if (punctuation.matches("[, ; : . ? !  ( )  [ ] \"]")) { // checks last character
                                                                                            // s for punctuation

					s = s.substring(0, s.length() - 1);
				} else {

					punctuation = "";
				}

				tokenizedSentence.add(s);
				charIdentifier.add("Word");

				if (!(punctuation.equals(""))) {

					tokenizedSentence.add(punctuation);
					charIdentifier.add("Punctuation");
				}

				tokenizedSentence.add(space);
				charIdentifier.add("space");
				punctuation = "";
			}
		}
		Tokenizer.tagger();
		Tokenizer.setup();
		Tokenizer.translate();
		
		return new Sentence(tokenizedSentence,charIdentifier);

		// for testing
		/*
		 * for(String b : brokenSentence) { System.out.println(b); }
		 * 
		 * for(String c: characterType){ System.out.println(c); }
		 */

	}
	public static void tagger() {
		for (int i = 0; i < tokenizedSentence.size(); i++) {

			tokenizedSentence.set(i, tagger.tagString(tokenizedSentence.get(i)));

			// System.out.println(tagged); //for testing
		}
	}

	public static void setup() {
		int indexOfIdentifier;
		for (int i = 0; i < tokenizedSentence.size(); i++) { // sets up charIdentifier for translating by removing
														  // underscore and adding tag to charIdentifier ArrayList

			if ((indexOfIdentifier = tokenizedSentence.get(i).indexOf("_")) != -1) {
				charIdentifier.set(i,tokenizedSentence.get(i).substring(indexOfIdentifier + 1));
				tokenizedSentence.set(i,tokenizedSentence.get(i).substring(0, indexOfIdentifier));
			}
		}

		Scanner scanner = null; // sets up translator array, first col contains
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
		scanner.close();
		
	}

	public static void translate() {

		for (int i = 0; i < charIdentifier.size(); i++) {
			for (int j = 0; j < key.length; j++) {
				if (charIdentifier.get(i).equals(key[j][0])) {
					charIdentifier.set(i, key[j][1]);
				}
			}
		}
}
}