// this class will contain static methods for detecting if a sentence contains
// subject-verb agreement errors regarding plurality

package commonwealth.errors;

import commonwealth.members.Clause;


public class SubjectVerbPluralityAgreement{
    
    //array of singular subjects
    //public static final String[] singularSUBJECTS = {};
    
    //array of singular vernss
    //public static final String[] singularVERBS = {};
    
    private static String error = "subject and verb plurality do not agree";
    
    public static String errorOf(Clause s){
        boolean flag = false;
        
        //checking method or alogrithm goes here
        //pretty sure that this will not work correctly
        //im still working on it
        //pls helperino
        
        //foreach(String subject : s.getSubjects()){
        //  forreach(String verb : s.getActions()){
        //      if(subject.ifSingularSubject() && verb.ifSingularVerb())
        //          return error;
        //      else if (!subject.ifSingularSubject() && !&& verb.ifSingularVerb())
        //          return error;
        //      else
        //          return "correct sva";
        //  }
        //}
        
        if(flag)
            return error;
        else
            return "";
    }
    
    //checks to see if the subject is part of the singular
    //subject array
    
    //public static boolean ifSingularSubject(String[] array){
    //   foreach (String sub : array){
    //    if (this.equals(sub))
    //        return true;
    //    else 
    //        return false;
    //    }
    //}
    
    //checks to see if the verb is part of the singular
    //action array
    
    //public static boolean ifSingularVerb(String[] array){
    //   foreach (String verb : array){
    //    if (this.equals(verb))
    //        return true;
    //    else 
    //        return false;
    //    }
    //}
    
}

