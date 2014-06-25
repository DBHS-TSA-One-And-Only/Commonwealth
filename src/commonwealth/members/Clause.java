// this is a Clause object
// all Clause objects must be passed through Identifier.isClause first
// so don't instantiate a clause object unless you already know it is a clause

package commonwealth.members;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Clause {
    
    private ArrayList<String> subjects, actions, clause, subjectIdentifier, actionIdentifier;
    
    public Clause(ArrayList<ArrayList<String>> clauseBundle){
        subjects = clauseBundle.get(0);				//subjects of clause
        subjectIdentifier = clauseBundle.get(1);	//identifiers of the subjects
        actions = clauseBundle.get(2);
        actionIdentifier = clauseBundle.get(3);
        clause = clauseBundle.get(4);				//original clause
    }
    
    public ArrayList<String> getSubjects(){
        return subjects;
    }
    
    public ArrayList<String> getSubjectIdentifiers(){
        return subjectIdentifier;
    }
    
    public ArrayList<String> getActions(){
        return actions;
    }
    
    public ArrayList<String> getActionIdentifiers(){
        return actionIdentifier;
    }
    
    public ArrayList<String> getClause(){
        return clause;
    }
    
    //initialize subordinatingConjuntions arraylist that holds a list of coordinating conjunctions 
    public ArrayList<String> initialize(ArrayList<String> subordinatingConjunctions){
    	Scanner scanner = null;
		try {
			scanner = new Scanner(new File("DBHS-TSA-One-And-Only\\Commonwealth\\subordinating conjunctions")).useDelimiter(",");
		} catch (FileNotFoundException e) {
			// print error msg via gui
		}

		while (scanner.hasNextLine()) {
			
				subordinatingConjunctions.add(scanner.nextLine());
		}
		
		return subordinatingConjunctions;
		
    }
    
    //checks if clause is independent
    public boolean isIndependent(){
    	ArrayList<String> subordinatingConjunctions = new ArrayList<>();
    	subordinatingConjunctions = this.initialize(subordinatingConjunctions);
    	for(String s: clause){
    		for(String sc: subordinatingConjunctions){
    			if(s.equalsIgnoreCase(sc)){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    
    //for testing
    public String toString(){
    	String compiledClause="";
    	for(String s: clause){
    		compiledClause = compiledClause + " " + s;
    	}
    	return compiledClause;
    }
    
}
