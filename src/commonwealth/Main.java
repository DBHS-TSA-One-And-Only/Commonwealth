package commonwealth;

import commonwealth.members.Clause;
import commonwealth.members.Sentence;
import commonwealth.sentencemanager.Identifier;
import commonwealth.sentencemanager.Splitter;
import commonwealth.sentencemanager.Tokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author forest
 */
public class Main {

	public static void main(String[] args) {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader scanner = new BufferedReader(input);
		String userInput = "";
		ArrayList<String> splitSentences = new ArrayList<>(); // contains
																// sentences

		ArrayList<Sentence> sentences = new ArrayList<>();
		ArrayList<Clause> clauses = new ArrayList<>();

		System.out.println("Type a sentence and press \'Enter\'");
		try {

			userInput = scanner.readLine(); // reads input
		} catch (IOException e) {

			e.printStackTrace();
		}

		splitSentences = Splitter.splitInput(userInput);
		System.out.println(splitSentences);
		for (String s : splitSentences) {
			sentences.add(Tokenizer.splitString(s));
		}
                
                //Tokenizer.spliString(s) returns blank arrays
                //However, when i put all the code in Tokenizer class into one main method and ran it, it was perfectly fine.
                
               
		for(Sentence s: sentences){
			System.out.println(s.getSentence());
		}

		for (Sentence s : sentences) {
			ArrayList<ArrayList<String>> splitStrings = Splitter
					.splitClauses(s);
			for (int i = 0; i < splitStrings.size(); i += 2) {
				if (Identifier.isClause(splitStrings.get(i),
						splitStrings.get(i + 1))) {
					ArrayList<ArrayList<String>> clauseBundle = new ArrayList<>();
					clauseBundle.add(Identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1))); // subjects found in clause
					clauseBundle.add(Identifier.actionsOf(splitStrings.get(i), splitStrings.get(i + 1))); // actions found in clause
					clauseBundle.add(splitStrings.get(i)); // clause itself
					clauses.add(new Clause(clauseBundle)); // create clause, add to arraylist of clauses
				}
			}
		}
		
		for(Clause c: clauses){
			System.out.println(c);
		}

	}

}