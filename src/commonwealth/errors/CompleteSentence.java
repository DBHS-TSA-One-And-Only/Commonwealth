

package commonwealth.errors;

import commonwealth.members.Sentence;

public class CompleteSentence{

    private static String error = "not a complete sentence";
    
    public static String errorOf(Sentence s){
        boolean flag = false;
        
        //checking method or alogrithm goes here
        
        if(flag)
            return error;
        else
            return "";
    }
    
}
