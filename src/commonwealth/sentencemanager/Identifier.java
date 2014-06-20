// this class contains methods used to identify subjects, actions, clauses, etc.

package commonwealth.sentencemanager;

import java.util.ArrayList;

public class Identifier {
	
	ArrayList<ArrayList<String>> clauses = new ArrayList<>(); 
	ArrayList<String> charIdentifier = new ArrayList<>();
	public static final String[] posKey = {"Singular or Mass Noun", "Singular Proper Noun", "Plural Proper Noun", "Plural Noun", "Personal Pronoun", "Possessive Pronoun", "Wh-pronoun", "Possessive wh-pronoun", "Wh-determiner"};
	
	public Identifier(ArrayList<ArrayList<String>> clause, ArrayList<String> identifier){
		clauses = clause;
		charIdentifier = identifier;
	}
    
    // this method returns whether a tokenized partial sentence is a clause or not
    public boolean isClause(ArrayList<String> potentialClause, ArrayList<String> identifiers){
    	if((this.subjectsOf(potentialClause, identifiers)!= null) && (this.actionsOf(potentialClause, identifiers)!= null))
    		return true;
    	return false;
    }
    
    // this method will return a list of subjects in the clause
    // the method will assume that input parameter "clause" is indeed a clause
    public ArrayList<String> subjectsOf(ArrayList<String> clause, ArrayList<String> charIdentifier){
    	ArrayList<String> subjects = new ArrayList<>();
        for(int index = 0; index< charIdentifier.size(); index++){
        	for(String s: posKey){
        		if(charIdentifier.get(index).matches(s)){
        			subjects.add(clause.get(index));
        		}
        	}
        }
        return subjects;
    }
    
    // this method will return a list of actions of the clause
    // assumption is made that input parameter "clause" is in fact a clause
    public ArrayList<String> actionsOf(ArrayList<String> clause, ArrayList<String> charIdentifier){
        
        return null;
    }
    
}
