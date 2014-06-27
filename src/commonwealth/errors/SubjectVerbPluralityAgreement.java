// this class will contain static methods for detecting if a sentence contains
// subject-verb agreement errors regarding plurality

package commonwealth.errors;

import commonwealth.members.Clause;
import java.util.ArrayList;

public class SubjectVerbPluralityAgreement {

	// array of singular subject tags
	public static final String[] SINGULAR_SUBJECT_TAGS = {
			"Singular or Mass Noun", "Singular Proper Noun","Wh-pronoun", "Wh-determiner" };

	// array of singular subjects
	public static final String[] SINGULAR_SUBJECTS = { "I", "You", "He", "She", "It" };

	// string of singular action tag
	public static final String SINGULAR_ACTION_TAG = "3rd person singular present Verb"; //"Verb base form";
        
        public static final String PAST_TENSE_TAG = "Verb past tense";

	
	public static String errorOf(Clause c) {
		String error = "subject and verb agreement error";

		// checking method or alogrithm goes here
		ArrayList<String> subjects = c.getSubjects();
		ArrayList<String> subjectIdentifiers = c.getSubjectIdentifiers();
		ArrayList<String> actionIdentifiers = c.getActionIdentifiers();
                
                
                for(int j = 0; j < actionIdentifiers.size(); j++){
                    if(pastTenseAction(actionIdentifiers.get(j))){
                        return "";
                    }
                }
                int i =0, index;
                if(subjectIdentifiers.size()<actionIdentifiers.size()){
                    index= subjectIdentifiers.size();
                }
                else{
                    index = actionIdentifiers.size();
                }
                while(i<index){
		//for (int i = 0; i < subjects.size(); i++) {
                    
			if((singularSubject(subjects.get(i), subjectIdentifiers.get(i)) == singularAction(actionIdentifiers
					.get(i)))) {
				return "";
				// replace with gui print method
			}
                        i++;
		}

		return error;
		// print out something good via gui
	}

	public static boolean singularSubject(String subject, String subjectIdentifier) {
		
		if (subjectIdentifier.equals("Verb gerund or present participle")){
			if(subject.endsWith("s")){
				return false;
			}
		}
		
		for (String s : SINGULAR_SUBJECT_TAGS) {
			if (subjectIdentifier.equals(s)) {
				return true;
			}
		}
		
		for (String s : SINGULAR_SUBJECTS) {
			if (subject.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean singularAction(String actionIdentifier) {

		if (actionIdentifier.equals(SINGULAR_ACTION_TAG)) {
			return true;
		}

		return false;
	}
        
        public static boolean pastTenseAction(String actionIdentifier){
            if(actionIdentifier.equals(PAST_TENSE_TAG)){
                return true;
            }
            return false;
        }

}