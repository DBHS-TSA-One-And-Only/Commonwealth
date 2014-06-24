// this class will contain static methods for detecting if a sentence contains
// subject-verb agreement errors regarding plurality

package commonwealth.errors;

import commonwealth.members.Sentence;


public class SubjectVerbPluralityAgreement{
    
    private static String error = "subject and verb plurality do not agree";
    
    public static String errorOf(Sentence s){
        boolean flag = false;
        
        //checking method or alogrithm goes here
        
        if(flag)
            return error;
        else
            return "";
    }
    
}
