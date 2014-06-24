package commonwealth;

import commonwealth.errors.CompleteSentence;
import commonwealth.errors.SubjectVerbPluralityAgreement;
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
		//System.out.println(splitSentences);//for testing
		//System.out.println("");
		//System.out.println("");
		boolean isFirst = true;
		
		for (String s : splitSentences) {
			//System.out.println(s);//for testing
			//System.out.println("");
			//System.out.println("");
			tokenizer = new Tokenizer(s);
			if(isFirst){
				tokenizer.initialize();
				isFirst = false;
			}
			sentences.add(tokenizer.run());
		}
		/*for(Sentence s: sentences){//for testing
			System.out.println(s.getSentence());
			System.out.println(s.getPosTags());
		}
		System.exit(0);*/
		
		
		
		ArrayList<ArrayList<String>> splitStrings;

		for (Sentence s : sentences) {
			//System.out.println(s.getSentence());//for testing
			 splitStrings = splitter.splitClauses(s);
			/*for(ArrayList<String> a: splitStrings){//for testing
				for(String z: a){
					System.out.println(z);
				}
				System.err.println("BREAK");
			}*/
			//System.exit(0);
			 
			 
			for (int i = 0; i < splitStrings.size(); i += 2) {
				if (identifier.isClause(splitStrings.get(i), splitStrings.get(i + 1))) {
					ArrayList<ArrayList<String>> clauseBundle = new ArrayList<>();
					
					System.out.println(identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1)));
					
					clauseBundle.add(identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1))); // subjects found in clause
					clauseBundle.add(identifier.actionsOf(splitStrings.get(i), splitStrings.get(i + 1))); // actions found in clause
					clauseBundle.add(splitStrings.get(i)); // clause itself
					clauses.add(new Clause(clauseBundle)); // create clause, add to arraylist of clauses
				}
				//System.out.println("BREAK");//for testing
			}
			
			splitStrings.clear();
			splitStrings= null;
			
			//System.out.println("BREAKERINO"); //for testing
		}
		
		/*for(Clause c: clauses){//for testing
			System.out.println(c);
		}*/

	}
        
        public static String[][] checkForErrors(ArrayList<Sentence> sentences){
            int numSentences = sentences.size();
            int numPossibleErrors = 2;
            
            String[][] errors = new String[numSentences][numPossibleErrors];
            
            for(int i = 0; i < 2; i++){
                errors[i][1] = CompleteSentence.errorOf(sentences.get(i));
            }
            
            for(int i = 0; i < 2; i++){
                errors[i][2] = SubjectVerbPluralityAgreement.errorOf(sentences.get(i));
            }
            
            return errors;
        }

}

