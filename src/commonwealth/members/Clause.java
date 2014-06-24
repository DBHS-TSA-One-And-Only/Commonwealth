// this is a Clause object
// all Clause objects must be passed through Identifier.isClause first
// so don't instantiate a clause object unless you already know it is a clause

package commonwealth.members;

import java.util.ArrayList;

public class Clause {
    
    private ArrayList<String> subjects, actions, clause, subjectIdentifier, actionIdentifier;
    
    public Clause(ArrayList<ArrayList<String>> clauseBundle){
        subjects = clauseBundle.get(0);
        subjectIdentifier = clauseBundle.get(1);	
        actions = clauseBundle.get(2);
        actionIdentifier = clauseBundle.get(3);
        clause = clauseBundle.get(4);
        // find subjects/actions using Identifier class and put into
        // subjects/actions private variables
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
    
    public String toString(){
    	String compiledClause="";
    	for(String s: clause){
    		compiledClause = compiledClause + " " + s;
    	}
    	return compiledClause;
    }
    
}
