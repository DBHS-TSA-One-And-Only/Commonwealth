// this is for creating Sentence objects

package commonwealth.members;

import java.util.ArrayList;


public class Sentence {
    
    private ArrayList<Clause> clauses;      //an arraylist of all the clauses in the sentence
    private ArrayList<String> tokenizedSentence, charIdentifier;
    String sentence;
    
    // constructor
    public Sentence(ArrayList<String> input, ArrayList<String> posTags){
    	tokenizedSentence = input;
    	charIdentifier = posTags;
        //split the sentences using Splitter class
        //put valid clausses in private "clauses" variable
    }
    
    public ArrayList<Clause> getClauses(){
        
        return clauses;
    }
    
    public ArrayList<String> getSentence(){
        return tokenizedSentence;
    }
    
    
    public ArrayList<String> getPosTags(){
    	return charIdentifier;
    }
    
    //for testing purposes
    public String toString(){
    	String compiledSentence="";
    	for(String s : tokenizedSentence){
    		compiledSentence = compiledSentence + s;
    	}
    	return compiledSentence;
    }
    
    //add more methods or something
    
}
