// this class contains methods used to identify subjects, actions, clauses, etc.

package commonwealth.sentencemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Identifier {

	public static final String[] SUBJECTS = { "Singular or Mass Noun",
		"Singular Proper Noun", "Plural Proper Noun", "Plural Noun",
		"Personal Pronoun", "Possessive Pronoun", "Wh-pronoun","Verb gerund or present participle",
		"Possessive wh-pronoun", "Wh-determiner" };
	public static final String[] VERBS = { "3rd person singular present Verb",
		"non-3rd person singular present Verb", "Verb past participle",
		 "Verb past tense", "Verb base form" };
	public static final String PREPOSITIONID = "Preposition or subordinating conjunction";
	public static ArrayList<String> prepositions;
	
	public Identifier(){
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("DBHS-TSA-One-And-Only\\Commonwealth\\prepositions")).useDelimiter(",");
		} catch (FileNotFoundException e) {
			// print error msg via gui
		}

		while (scanner.hasNextLine()) {
			
				prepositions.add(scanner.nextLine());
				
		}
	}

	// this method returns whether a tokenized partial sentence is a clause or not
	public boolean isClause(ArrayList<String> potentialClause, ArrayList<String> charIdentifier) {
		if ((this.subjectsOf(potentialClause, charIdentifier).isEmpty())
				&& (this.actionsOf(potentialClause, charIdentifier).isEmpty()))
			return false;
		return true;
	}

	// this method will return a list of subjects in the clause
	// the method will assume that input parameter "clause" is indeed a clause
	public ArrayList<ArrayList<String>> subjectsOf(ArrayList<String> clause, ArrayList<String> charIdentifier) {
		boolean hasPrep = false, hasVerb = false;
		ArrayList<String> subjects = new ArrayList<>(), identifiers = new ArrayList<>();
		for (int index = 0; index < clause.size(); index++) {
			for(String s: prepositions)
				if(clause.get(index).equals(s)){
					hasPrep = true;
					break;
			}
			for (String s : VERBS) {
				if (charIdentifier.get(index).equals(s)) {
					hasVerb = true;
				}
			}
			if(!hasPrep){
				if(!hasVerb){
					for (String s : SUBJECTS) {
						if (charIdentifier.get(index).equals(s)) {
							subjects.add(clause.get(index));
							identifiers.add(charIdentifier.get(index));
							hasVerb = false;
							hasPrep = false;
						}
					}
				}
			}
		}
		
		ArrayList<ArrayList<String>> subjectBundle = new ArrayList<>();
		subjectBundle.add(subjects);
		subjectBundle.add(identifiers);
		
		return subjectBundle;
	}
	
	
	


	// this method will return a list of actions of the clause
	// assumption is made that input parameter "clause" is in fact a clause
	public ArrayList<ArrayList<String>> actionsOf(ArrayList<String> clause, ArrayList<String> charIdentifier) {
		ArrayList<String> actions = new ArrayList<>(), identifiers = new ArrayList<>();
		for (int index = 0; index < charIdentifier.size(); index++) {
			for (String s : VERBS) {
				if (charIdentifier.get(index).equals(s)) {
					actions.add(clause.get(index));
					identifiers.add(clause.get(index));
				}
			}
		}
		
		ArrayList<ArrayList<String>> actionBundle = new ArrayList<>();
		actionBundle.add(actions);
		actionBundle.add(identifiers);
		
		return actionBundle;
	}

}