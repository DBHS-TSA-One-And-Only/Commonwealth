package commonwealth;

import commonwealth.errors.CompleteSentence;
import commonwealth.errors.SubjectVerbPluralityAgreement;
import commonwealth.gui.CommonwealthGUI;
import commonwealth.members.Clause;
import commonwealth.members.Sentence;
import commonwealth.sentencemanager.Identifier;
import commonwealth.sentencemanager.Splitter;
import commonwealth.sentencemanager.Tokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	static CommonwealthGUI GUI = new CommonwealthGUI();
	static String pls = "";
	
	public static void main(String[] args) {

		
	
		
            try {
                GUI.start();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
	//	Thread t1 = new Thread();
		pls = GUI.inputTextField.getText();
	
	}
	public static void start(String fal) {
		//InputStreamReader input = new InputStreamReader(System.in);
		//BufferedReader scanner = new BufferedReader(input);
	
		String userInput = fal;
		ArrayList<String> splitSentences = new ArrayList<>(); // contains sentences
		ArrayList<Sentence> sentences = new ArrayList<>();
		//ArrayList<Clause> clauses = new ArrayList<>();
		Tokenizer tokenizer = new Tokenizer();
		Splitter splitter = new Splitter();
		//Identifier identifier = new Identifier();
		//String[][] errorList;
		
	
		/*try {
			GUI.inputTextField.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//System.out.println("Type a sentence and press \'Enter\'");
		//try {
		/*
			userInput = scanner.readLine(); // reads input
		} catch (IOException e) {

			//print gui stuff here
		}
			*/

		splitSentences = splitter.splitInput(userInput);
		//System.out.println(splitSentences);//for testing
		//System.out.println("");
		//System.out.println("");

		Identifier.initialize();
		Tokenizer.initialize();
		for (String s : splitSentences) {
                    try {
                        /*System.out.println(s);
                        System.out.println("  a  ");
                        System.out.println("");*/
                        sentences.add(tokenizer.run(s));
                        
                        
                        //System.out.println("");
                        //System.out.println("");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
		
		//System.out.println(userInput);
		//System.exit(0);
		GUI.print(printErrors(formatErrors(checkForErrors(sentences))));
		System.out.println(printErrors(formatErrors(checkForErrors(sentences))));
		
		
		
	}
	
/*	class sleeper extends Thread{
		public void run(){
			userInput = GUI.inputTextField.getText();
		}
	}*/
	
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
			errorIdentifiers = "";
		}
		
		return allErrors;
	}
	
	public static String printErrors(ArrayList<String> errors){
		int sentenceNum = 1;
		String errorMessage = "";
		for(int i = 0; i < errors.size(); i++){
			if(!(errors.get(i).matches("[| ]"))){
				errorMessage = errorMessage + "Sentence " + sentenceNum + ": " + errors.get(i);
			}
			sentenceNum++;
		}
		return errorMessage;
	}

}