// this class contains methods used to identify subjects, actions, clauses, etc.
package commonwealth.sentencemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Identifier {

    public static final String[] SUBJECTS = {"Singular or Mass Noun",
        "Singular Proper Noun", "Plural Proper Noun", "Plural Noun",
        "Personal Pronoun", "Wh-pronoun", "Verb gerund or present participle", 
        "Wh-determiner"};
    public static final String[] VERBS = {"3rd person singular present Verb",
        "non-3rd person singular present Verb", "Verb past participle",
        "Verb past tense", "Verb base form"};
    public static ArrayList<String> prepositions = new ArrayList<>();

    //initializes prepositions arraylist
    public static void initialize() {
        Scanner scanner = null;
        try {

            scanner = new Scanner(new File("prepositions")).useDelimiter(",");
        } catch (FileNotFoundException e) {

        }

        while (scanner.hasNext()) {

            prepositions.add(scanner.next());

        }
    }

    // this method returns whether a tokenized partial sentence is a clause or not
    public boolean isClause(ArrayList<String> potentialClause, ArrayList<String> charIdentifier) {
        for (int i = 0; i < this.subjectsOf(potentialClause, charIdentifier).size(); i++) {
            if ((this.subjectsOf(potentialClause, charIdentifier).get(i).isEmpty())
                    || (this.actionsOf(potentialClause, charIdentifier).get(i).isEmpty())) {
                return false;
            }
        }
        return true;
    }

    // this method will return a list of subjects in the clause
    // the method will assume that input parameter "clause" is indeed a clause
    public ArrayList<ArrayList<String>> subjectsOf(ArrayList<String> clause, ArrayList<String> charIdentifier) {
        boolean hasPrep = false, hasVerb = false;
        ArrayList<String> subjects = new ArrayList<>(), identifiers = new ArrayList<>();
        for (int index = 0; index < clause.size(); index++) {
            for (String s : prepositions) {
                if (clause.get(index).replaceAll("\\s", "").equalsIgnoreCase(s)) {
                    hasPrep = true;
                    break;
                }
            }
            for (String s : VERBS) {
                if (charIdentifier.get(index).equals(s)) {
                    hasVerb = true;
                    break;
                }
            }

            if (!hasVerb) {
                for (String s : SUBJECTS) {
                    if (charIdentifier.get(index).equals(s)) {
                        if (!hasPrep) {
                            subjects.add(clause.get(index));
                            identifiers.add(charIdentifier.get(index));
                        } else {
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
                    identifiers.add(charIdentifier.get(index));
                }
            }
        }

        ArrayList<ArrayList<String>> actionBundle = new ArrayList<>();
        actionBundle.add(actions);
        actionBundle.add(identifiers);

        return actionBundle;
    }
}
