package commonwealth.sentencemanager;

import commonwealth.members.Sentence;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tokenizer {

	static String sentence = "", punctuation = "";
	static ArrayList<String> tokenizedSentence = new ArrayList<>(),charIdentifier = new ArrayList<>(); // charIdentifier contains labels
																									   // for corresponding values in tokenizedSentence
	static MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
	static String[][] key = new String[40][2];
	static String[][] contractions = new String[89][2];

	public static Sentence splitString(String input) {

		Tokenizer.initialize();
		
		ArrayList<String> sentenceArray;

		// splits sentence into single words at spaces
		// Note: will also bring punctuation with word (if applicable)
		// i.e. "Hi, I'm Akali" will split into "Hi,", "I'm", and "Akali"
		sentenceArray = new ArrayList<>(Arrays.asList(sentence.split("[@ _ \\t\r]")));
		for(int i =0; i < sentenceArray.size(); i++){
			for(int r = 0; r < contractions.length;r++){
				if(sentenceArray.get(i).equals(contractions[r][0])){
					sentenceArray.set(i, contractions[r][1].split(" ")[0]);
					sentenceArray.add(i+1, contractions[r][1].split(" ")[1]);
				}
			}
			
		}

		for (String s : sentenceArray) {

			if (!(s.equals(""))) {

				// checks last char of s for punctuation
				punctuation = s.substring(s.length() - 1);
				if (punctuation.matches("[, ; : . ? !  ( )  [ ] \" ]")) {

					s = s.substring(0, s.length() - 1);
				} else {

					punctuation = "";
				}
				if(s.substring(0,1).matches("[, ; : ( [ ] ) \"]")){
					tokenizedSentence.add(s.substring(0,1));
					charIdentifier.add("Punctuation");
					tokenizedSentence.add(s.substring(1,s.length()));
					charIdentifier.add("Word");
					tokenizedSentence.add(" ");
					charIdentifier.add("space");
					punctuation = "";
					continue;
				
				} 

				tokenizedSentence.add(s);
				charIdentifier.add("Word");

				if (!(punctuation.equals(""))) {

					tokenizedSentence.add(punctuation);
					charIdentifier.add("Punctuation");
				}

				tokenizedSentence.add(" ");
				charIdentifier.add("space");
				punctuation = "";

			}

		}
		Tokenizer.tagger();
		Tokenizer.setup();
		Tokenizer.translate();

		return new Sentence(tokenizedSentence, charIdentifier);

		// for testing
		/*
		 * for(String b : brokenSentence) { System.out.println(b); }
		 * 
		 * for(String c: characterType){ System.out.println(c); }
		 */

	}

	// tags words in sentence
	public static void tagger() {
		for (int i = 0; i < tokenizedSentence.size(); i++) {
			tokenizedSentence.set(i, tagger.tagString(tokenizedSentence.get(i)));
			// System.out.println(tagged); //for testing
		}
	}

	// sets up charIdentifier for translating by removing underscores and spaces
	

	public static void setup() {
		int indexOfIdentifier;
		for (int i = 0; i < tokenizedSentence.size(); i++) {

			if ((indexOfIdentifier = tokenizedSentence.get(i).indexOf("_")) != -1) {
				
				
				if((tokenizedSentence.get(i).substring(indexOfIdentifier + 1, indexOfIdentifier + 2).matches("[, : . $ # ( ) \"]"))){ 
					charIdentifier.set(i,tokenizedSentence.get(i).substring(0, indexOfIdentifier));
					tokenizedSentence.set(i,tokenizedSentence.get(i).substring(0, indexOfIdentifier));
				}
				else{
					charIdentifier.set(i,tokenizedSentence.get(i).substring(indexOfIdentifier + 1));
					tokenizedSentence.set(i,tokenizedSentence.get(i).substring(0, indexOfIdentifier));
				}
			}
		}
		//removes whitespaces
		for(int i =0; i < charIdentifier.size(); i ++){
			charIdentifier.set(i, charIdentifier.get(i).replaceAll("\\s",""));
		}
	}
	
		// underscore and adding tag to charIdentifier ArrayList
		// Also sets up translator array, first col contains
		// tag, second col contains translated meaning
		// Lastly, sets up contraction array, first col contraction, second col split contraction
	public static void initialize(){
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("DBHS-TSA-One-And-Only\\Commonwealth\\postaggerkey"));
		} catch (FileNotFoundException e) {
			// print error msg via gui
		}

		while (scanner.hasNextLine()) {

			int indexRow = 0, indexCol = 0;
			@SuppressWarnings("resource")
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
		
		try {
			scanner = new Scanner(new File("DBHS-TSA-One-And-Only\\Commonwealth\\contractions"));
		} catch (FileNotFoundException e) {
			// print error msg via gui
		}

		while (scanner.hasNextLine()) {

			int indexRow = 0, indexCol = 0;
			@SuppressWarnings("resource")
			Scanner scanner2 = new Scanner(scanner.nextLine())
					.useDelimiter(",");

			while (scanner2.hasNext()) {
				contractions[indexRow][indexCol] = scanner2.next();

				if (indexCol == 0) {
					indexCol = 1;
				} else {
					indexCol = 0;
					indexRow++;
				}
			}
		}
		scanner.close();
	}

	// translates tags into normal english

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