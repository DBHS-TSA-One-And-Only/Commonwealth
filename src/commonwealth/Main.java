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
import java.util.logging.Level;
import java.util.logging.Logger;

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
		//String[][] errorList;

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
		Identifier.initialize();
		Tokenizer.initialize();
		for (String s : splitSentences) {
			System.out.println(s);
			System.out.println("  a  ");
			System.out.println("");
			
			if(isFirst){
				
				isFirst = false;
			}
                    try {
                        sentences.add(tokenizer.run(s));
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
			for(int i = 0; i< 1; i ++){
				for(String z: sentences.get(i).getSentence()){
					System.out.println(z);
				}
			}
			
			System.out.println("  b  ");
			System.out.println("");
		}
		
		
		
		System.out.println(formatErrors(checkForErrors(sentences)));
		
		
	}
	
	public static String[][] checkForErrors(ArrayList<Sentence> sentences){
        int numSentences = sentences.size();
        int numPossibleErrors = 2;
        int i=0;
        String[][] errors = new String[numSentences][numPossibleErrors];
        
        
        for(Sentence s: sentences){
        	if(s.getClauses()!= null){
        		for(int j =0; j < s.getClauses().size(); j ++){
        			errors[i][0] = CompleteSentence.errorOf(s.getClauses());
        			errors[i][1] = SubjectVerbPluralityAgreement.errorOf(s.getClauses().get(j));
        		}
        	}
        }
        
        return errors;
    }
	
	public static ArrayList<String> formatErrors(String[][] errors){
		String errorIdentifiers = "";
		ArrayList<String> allErrors = new ArrayList<>();
		for(int i =0; i < errors.length; i++){
			for(int j =0; j <errors[i].length; j++){
				
					errorIdentifiers = errorIdentifiers + errors[i][j] + "\n";
			}
			allErrors.add(errorIdentifiers);
		}
		
		return allErrors;
	}
	
	public static String printErrors(ArrayList<String> errors){
		int sentenceNum = 1;
		String errorMessage = "";
		for(int i = 0; i < errors.size(); i++){
			if(!(errors.get(i).equals(""))){
				errorMessage = errorMessage + "Sentence " + sentenceNum + ": " + errors.get(i);
			}
			sentenceNum++;
		}
		return null;
	}

}


