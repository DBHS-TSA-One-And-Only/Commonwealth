package commonwealth;

import commonwealth.members.Clause;
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
		String sentence = "";
		ArrayList<ArrayList<String>> splitStrings = new ArrayList<>(); // contains split fragments of sentences,
																	   // may or may not be clauses
		ArrayList<Clause> clauses = new ArrayList<>();

		System.out.println("Type a sentence and press \'Enter\'");
		try {

			sentence = scanner.readLine(); // reads input
		} catch (IOException e) {

			e.printStackTrace();
		}

		splitStrings = Splitter.splitClauses(Tokenizer.splitString(sentence).get(0), 
				Tokenizer.splitString(sentence).get(1));
		
		for (int i = 0; i < splitStrings.size() - 1; i++) {
			if (Identifier.isClause(splitStrings.get(i), splitStrings.get(i + 1))) {
				ArrayList<ArrayList<String>> clauseBundle = new ArrayList<>();
				clauseBundle.add(Identifier.subjectsOf(splitStrings.get(i),splitStrings.get(i + 1)));		//subjects found in clause
				clauseBundle.add(Identifier.actionsOf(splitStrings.get(i),splitStrings.get(i + 1)));		//actions found in clause
				clauseBundle.add(splitStrings.get(i));														//clause itself
				clauses.add(new Clause(clauseBundle));														//create clause, add to arraylist of clauses
			}
		}
		

	}

}
