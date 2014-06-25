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
		//ArrayList<Clause> clauses = new ArrayList<>();
		Tokenizer tokenizer = new Tokenizer();
		Splitter splitter = new Splitter();
		//Identifier identifier = new Identifier();

		System.out.println("Type a sentence and press \'Enter\'");
		try {

			userInput = scanner.readLine(); // reads input
		} catch (IOException e) {

			//print gui stuff here
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
			
			if(isFirst){
				tokenizer.initialize();
				isFirst = false;
			}
			sentences.add(tokenizer.run(s));
		}
		
	}
	
	public String[][] checkForErrors(ArrayList<Sentence> sentences){
        int numSentences = sentences.size();
        int numPossibleErrors = 2;
        int i=0;
        String[][] errors = new String[numSentences][numPossibleErrors];
        
        
        for(Sentence s: sentences){
        	for(int j =0; j < s.getClauses().size(); j ++){
        		errors[i][0] = CompleteSentence.errorOf(s.getClauses().get(j));
        		errors[i][1] = SubjectVerbPluralityAgreement.errorOf(s.getClauses().get(j));
        	}
        }
        
        return errors;
    }

}

