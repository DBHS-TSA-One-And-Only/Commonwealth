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

public class Main {

	public static void main(String[] args) {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader scanner = new BufferedReader(input);
		String userInput = "";
		ArrayList<String> splitSentences = new ArrayList<>(); // contains sentences
		ArrayList<Sentence> sentences = new ArrayList<>();
		ArrayList<Clause> clauses = new ArrayList<>();
		Tokenizer tokenizer;
		Splitter splitter = new Splitter();
		Identifier identifier = new Identifier();

		System.out.println("Type a sentence and press \'Enter\'");
		try {

			userInput = scanner.readLine(); // reads input
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		
		

		splitSentences = splitter.splitInput(userInput);
		//System.out.println(splitSentences);
		//System.out.println("");
		//System.out.println("");
		boolean isFirst = true;
		
		for (String s : splitSentences) {
			//System.out.println(s);
			//System.out.println("");
			//System.out.println("");
			tokenizer = new Tokenizer(s);
			if(isFirst){
				tokenizer.initialize();
				isFirst = false;
			}
			sentences.add(tokenizer.run());
		}
		/*for(Sentence s: sentences){
			System.out.println(s.getSentence());
		}*/
                
                //PLS HELP RIGHT HERE

		for (Sentence s : sentences) {
			System.out.println(s.getSentence());
			ArrayList<ArrayList<String>> splitStrings = new ArrayList<>(splitter.splitClauses(s));
			for(ArrayList<String> a: splitStrings){
				for(String z: a){
					System.out.println(z);
				}
				System.err.println("BREAK");
			}
			//System.exit(0);
			for (int i = 0; i < splitStrings.size(); i += 2) {
				if (identifier.isClause(splitStrings.get(i),
						splitStrings.get(i + 1))) {
					ArrayList<ArrayList<String>> clauseBundle = new ArrayList<>();
					
					System.out.println(identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1)));
					
					clauseBundle.add(identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1))); // subjects found in clause
					clauseBundle.add(identifier.actionsOf(splitStrings.get(i), splitStrings.get(i + 1))); // actions found in clause
					clauseBundle.add(splitStrings.get(i)); // clause itself
					clauses.add(new Clause(clauseBundle)); // create clause, add to arraylist of clauses
				}
				//System.out.println("BREAK");
			}
			splitStrings.clear();
			
			//System.out.println("BREAKERINO");
		}
		
		/*for(Clause c: clauses){
			System.out.println(c);
		}*/

	}

}
