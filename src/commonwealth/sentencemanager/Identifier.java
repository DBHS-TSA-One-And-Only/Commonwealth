// this class contains methods used to identify subjects, actions, clauses, etc.

package commonwealth.sentencemanager;

import java.util.ArrayList;

public class Identifier {

	public static final String[] SUBJECTS = { "Singular or Mass Noun",
			"Singular Proper Noun", "Plural Proper Noun", "Plural Noun",
			"Personal Pronoun", "Possessive Pronoun", "Wh-pronoun",
			"Possessive wh-pronoun", "Wh-determiner" };
	public static final String[] VERBS = { "3rd person singular present Verb",
			"non-3rd person singular present Verb", "Verb past participle",
			"Verb gerund or present participle", "Verb past tense",
			"Verb base form" };

	// this method returns whether a tokenized partial sentence is a clause or not
	public static boolean isClause(ArrayList<String> potentialClause, ArrayList<String> charIdentifier) {
		if ((Identifier.subjectsOf(potentialClause, charIdentifier) != null)
				&& (Identifier.actionsOf(potentialClause, charIdentifier) != null))
			return true;
		return false;
	}

	// this method will return a list of subjects in the clause
	// the method will assume that input parameter "clause" is indeed a clause
	public static ArrayList<String> subjectsOf(ArrayList<String> clause, ArrayList<String> charIdentifier) {
		ArrayList<String> subjects = new ArrayList<>();
		for (int index = 0; index < charIdentifier.size(); index++) {
			for (String s : SUBJECTS) {
				if (charIdentifier.get(index).matches(s)) {
					subjects.add(clause.get(index));
				}
			}
		}
		return subjects;
	}

	// this method will return a list of actions of the clause
	// assumption is made that input parameter "clause" is in fact a clause
	public static ArrayList<String> actionsOf(ArrayList<String> clause, ArrayList<String> charIdentifier) {
		ArrayList<String> actions = new ArrayList<>();
		for (int index = 0; index < charIdentifier.size(); index++) {
			for (String s : VERBS) {
				if (charIdentifier.get(index).matches(s)) {
					actions.add(clause.get(index));
				}
			}
		}
		return actions;
	}

}