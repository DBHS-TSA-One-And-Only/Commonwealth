//not finished yet, pushed for gui testing

import java.io.*;
import java.util.ArrayList;

public class Tokenizer {

	public static void main(String[] args) {

		System.out.println("Type a sentence and press 'Enter.'");

		String sentence = "", space = " ", punctuation = "";
		ArrayList<String> brokenSentence = new ArrayList<>();
		ArrayList<String> characterType = new ArrayList<>();
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader scanner = new BufferedReader(input);

		try {

			sentence = scanner.readLine(); // reads user input
		} catch (IOException e) {

			e.printStackTrace();
		}

		for (String s : sentence.split("[@ \\t\r]")) { /* splits sentence into single words at spaces (not punctuations anymore QQ)
								*  					
								* Note: will also bring punctuation with word. ie(Hi, I'm Akali 
                                                                * will split into "Hi,", "I'm" , and "Akali"
                                                                */
			if (!(s.equals(""))) {

				punctuation = s.substring(s.length() - 1);          
				if (punctuation.matches("[, ; : . ? ! \" \']")) {   // checks last character of s for punctuation
																	 
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
		for (String b : brokenSentence) {
			System.out.println(b);
		}
		
		for(String c: characterType){
			System.out.println(c);
		}
		
	}
}
