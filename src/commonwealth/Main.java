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
			GUI.run();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	//	Thread t1 = new Thread();
		pls = GUI.inputTextField.getText();
	
	}
	public static String start(String input) {
		//InputStreamReader input = new InputStreamReader(System.in);
		//BufferedReader scanner = new BufferedReader(input);
	
		String userInput = input;
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
			//System.out.println(s);
			/*System.out.println("  a  ");
			System.out.println("");*/
			sentences.add(tokenizer.run(s));
                      //  for(String z: (tokenizer.run(s)).getPosTags()){
                        //System.out.println(z);
                        //}
			/*System.exit(0);
			int i =0;
			for(String z: sentences.get(i).getClauses().get(0).getActions()){
				System.out.println(z);
			}*/
			
		}
		//System.out.println(sentences.size());
		//System.out.println(userInput);
		//System.exit(0);
		//GUI.print(printErrors(formatErrors(checkForErrors(sentences))));
		//System.out.println(printErrors(formatErrors(checkForErrors(sentences))));
		          //System.out.println(sentences.get(0).getClauses().get(5));
		return printErrors(formatErrors(checkForErrors(sentences)));
		
	}
	
/*	class sleeper extends Thread{
		public void run(){
			userInput = GUI.inputTextField.getText();
		}
	}*/
	
	public static String[][] checkForErrors(ArrayList<Sentence> sentences){
        int numSentences = sentences.size();
        int numPossibleErrors = 2;
        int i=0, j = 1;
        String[][] errors = new String[numSentences][numPossibleErrors];
        
       // Identifier id = new Identifier();
        for(Sentence s: sentences){
        	if(s.getClauses()!= null){
        		/*for(Clause c: s.getClauses()){
        			System.out.println(id.actionsOf(c.getClause(), s.getPosTags()).get(0).isEmpty());
        			for(String z: s.getPosTags())
        				System.out.println(z);
        			System.out.println(id.isClause(c.getClause(), s.getPosTags()));
        		}*/
        		errors[i][0] = CompleteSentence.errorOf(s.getClauses());
        		//System.out.println(errors[i][0]);
        		if(s.getClauses().size()!=0){
                            for(j =1; j < s.getClauses().size()+1; j ++){
                                //for(String c: s.getClauses().get(j-1).getSubjectIdentifiers()){
                                   // System.out.println(c);
                                //}
                               /* Identifier identifier = new Identifier();
                                for(int z = 0; z <s.getClauses().size(); z++){
                                  System.out.println((identifier.actionsOf(s.getClauses().get(z).getActions(), 
                                            s.getClauses().get(z).getActionIdentifiers())).get(1).get(0));
                                
                                    System.exit(0);*/
        			errors[i][j] = SubjectVerbPluralityAgreement.errorOf(s.getClauses().get(j-1));
        		//	System.out.println(errors[i][j]);
                          //      System.out.println(j);
                                
                                //}
                            }
        		
        		
        		}else{
        			if(!errors[i][0].equals("not a complete sentence"))
        				errors[i][j] = "not a complete sentence";
        		}
        		i++;
        	}
        	
        }
        //System.out.println(errors[i-1][0]);
        //System.out.println(errors[i-1][1]);
       // System.exit(0);
       /* for(int z =0; z < errors.length; z++){
			for(int j =0; j <errors[z].length; j++){
				System.out.println(errors[0][1]);
			}
        }
        System.out.println("break");*/
        
        return errors;
    }
	
	public static ArrayList<String> formatErrors(String[][] errors){
		String errorIdentifiers = "";
		ArrayList<String> allErrors = new ArrayList<>();
                System.out.println(errors[0].length);
		for(int i =0; i < errors.length; i++){
			for(int j =0; j <errors[i].length ; j++){
                          //  System.out.println(errors[i][j]);
                            //System.out.println("blank");
                            if(errors[i][j]!=null){
					if(!(errors[i][j].equals(""))){
						errorIdentifiers = errorIdentifiers + errors[i][j] + "\n";
              //                                  System.out.println(errorIdentifiers);
					}
                            }
			
		
			}
	//		System.out.println(errorIdentifiers);
			if(errorIdentifiers.equals("")){
				errorIdentifiers = "no errors found" + "\n";
			}
			allErrors.add(errorIdentifiers);
			errorIdentifiers = "";
		}
		//System.out.println(allErrors);
		return allErrors;
	}
	
	public static String printErrors(ArrayList<String> errors){
		int sentenceNum = 1;
		String errorMessage = "";
		for(int i = 0; i < errors.size(); i++){
			
			errorMessage = errorMessage + "Sentence " + sentenceNum + ": " + errors.get(i) ;
			
			sentenceNum++;
		}
		System.out.println(errorMessage);
		return errorMessage;
	}

}
