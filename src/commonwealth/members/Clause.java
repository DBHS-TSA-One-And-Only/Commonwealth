// this is a Clause object
// all Clause objects must be passed through Identifier.isClause first
// so don't instantiate a clause object unless you already know it is a clause

package commonwealth.members;

import java.util.ArrayList;

public class Clause {
    
    private ArrayList<String> subjects;
    private ArrayList<String> actions;
    private ArrayList<String> clause;
    
    public Clause(ArrayList<String> tokenizedClause){
        clause = tokenizedClause;
        // find subjects/actions using Identifier class and put into
        // subjects/actions private variables
    }
    
    public ArrayList<String> getSubjects(){
        return subjects;
    }
    
    public ArrayList<String> getActions(){
        return actions;
    }
    
    public ArrayList<String> getClause(){
        return clause;
    }
    
}
