// this class contains methods used to identify subjects, actions, clauses, etc.

package commonwealth.sentencemanager;

import java.util.ArrayList;


public class Identifier {
    
    // this method returns whether a tokenized partial sentence is a clause or not
    public static boolean isClause(ArrayList<String> potentialClause){
        return true;
    }
    
    // this method will return a list of subjects in the clause
    // the method will assume that input parameter "clause" is indeed a clause
    public ArrayList<String> subjectsOf(ArrayList<String> clause){
        
        return null;
    }
    
    // this method will return a list of actions of the clause
    // assumption is made that input parameter "clause" is in fact a clause
    public ArrayList<String> actionsOf(ArrayList<String> clause){
        
        return null;
    }
    
}
