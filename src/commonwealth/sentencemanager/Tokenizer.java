package commonwealth.sentencemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Tokenizer {
	
	String sentence = "", space = " ", punctuation = "";
	ArrayList<String> brokenSentence = new ArrayList<>(), characterType = new ArrayList<>(); //characterType cotains labels for corresponding 
	InputStreamReader input = new InputStreamReader(System.in);								 //elements in brokenSentence
	BufferedReader scanner = new BufferedReader(input);
	

	MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
	
	String[][] key = new String[40][2];
	public void splitString() {                                             //splits string into individual words and punctuation

		try {

			sentence = scanner.readLine(); // reads input
		} catch (IOException e) {

			e.printStackTrace();
		}

		for (String s : sentence.split("[@ _ \\t\r]")) { /*
														 * splits sentence into single words at
														 * spaces (not punctuations anymore QQ)
														 * 
														 * Note: will also bring punctuation with
														 * word. ie "Hi, I'm Akali" will split into
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

		

		// for testing
		/*
		 * for(String b : brokenSentence) { System.out.println(b); }
		 * 
		 * for(String c: characterType){ System.out.println(c); }
		 */

	}
	public void tagger() {                                                  //uses pos tagger to tag words in brokenSentence ArrayList
		for (int i = 0; i < brokenSentence.size(); i++) {

			brokenSentence.set(i, tagger.tagString(brokenSentence.get(i)));

			// System.out.println(tagged); //for testing
		}
	}

	public void setup() {                                                   // sets up charIdentifier for translating by removing
		int indexOfIdentifier;                                          // underscore and adding tag to charIdentifier ArrayList
		for (int i = 0; i < brokenSentence.size(); i++) {                  
														  

			if ((indexOfIdentifier = brokenSentence.get(i).indexOf("_")) != -1) {
				characterType.set(i,brokenSentence.get(i).substring(indexOfIdentifier + 1));
				brokenSentence.set(i,brokenSentence.get(i).substring(0, indexOfIdentifier));
			}
		}

		Scanner scanner = null; // sets up translator array, first col contains
								// tag, second col contains translated meaning
		try {
			scanner = new Scanner(new File("C:\\Users\\forest\\New folder\\postagger\\postaggerkey"));
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

	public void translate() {                                               //translates the pos tags into normal english

		for (int i = 0; i < characterType.size(); i++) {
			for (int j = 0; j < key.length; j++) {
				if (characterType.get(i).equals(key[j][0])) {
					characterType.set(i, key[j][1]);
				}
			}
		}
}
}
