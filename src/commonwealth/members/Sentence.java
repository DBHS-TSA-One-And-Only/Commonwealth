// this is for creating Sentence objects

package commonwealth.members;

import java.util.ArrayList;


public class Sentence {
    
    private ArrayList<Clause> clauses;      //an arraylist of all the clauses in the sentence
    private ArrayList<String> sentence;
    
    // constructor
    public Sentence(ArrayList<String> tokenizedSentence){
        //split the sentences using Splitter class
        //put valid clausses in private "clauses" variable
    }
    
    public ArrayList<Clause> getClauses(){
        return clauses;
    }
    
    public ArrayList<String> getSentence(){
        return sentence;
    }
    
    //add more methods or something
    
}
