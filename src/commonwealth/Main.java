package commonwealth;

import commonwealth.errors.CompleteSentence;
import commonwealth.errors.SubjectVerbPluralityAgreement;
import commonwealth.gui.CommonwealthGUI;
import commonwealth.members.Clause;
import commonwealth.members.Sentence;
import commonwealth.sentencemanager.Identifier;
import static commonwealth.sentencemanager.Identifier.SUBJECTS;
import static commonwealth.sentencemanager.Identifier.VERBS;
import static commonwealth.sentencemanager.Identifier.prepositions;
import commonwealth.sentencemanager.Splitter;
import commonwealth.sentencemanager.Tokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static CommonwealthGUI GUI = new CommonwealthGUI();

    public static void main(String[] args) {

        try {
            GUI.run();
        } catch (FileNotFoundException e1) {
            GUI.print("File not found");
        }

    }

    //takes in input, splits, tags, calls printing methods
    public static String start(String input) {

        String userInput = input;
        ArrayList<String> splitSentences = new ArrayList<>(); // contains sentences
        ArrayList<Sentence> sentences = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer();
        Splitter splitter = new Splitter();

        splitSentences = splitter.splitInput(userInput);

        Identifier.initialize();
        Tokenizer.initialize();
        for (String s : splitSentences) {

            sentences.add(tokenizer.run(s));

        }

        return printErrors(formatErrors(checkForErrors(sentences)));

    }

    //checks sentences for input
    public static String[][] checkForErrors(ArrayList<Sentence> sentences) {
        int numSentences = sentences.size();
        int numPossibleErrors = 2;
        int i = 0, j = 1;
        String[][] errors = new String[numSentences][numPossibleErrors];

        for (Sentence s : sentences) {
            if (s.getClauses() != null) {
                /*for(Clause c: s.getClauses()){
                 System.out.println(id.actionsOf(c.getClause(), s.getPosTags()).get(0).isEmpty());
                 for(String z: s.getPosTags())
                 System.out.println(z);
                 System.out.println(id.isClause(c.getClause(), s.getPosTags()));
                 }*/
                errors[i][0] = CompleteSentence.errorOf(s.getClauses());
                //System.out.println(errors[i][0]);
                if (s.getClauses().size() != 0) {
                    for (j = 1; j < s.getClauses().size() + 1; j++) {

                        errors[i][j] = SubjectVerbPluralityAgreement.errorOf(s.getClauses().get(j - 1));

                    }

                } else {
                    if (!errors[i][0].equals("not a complete sentence")) {
                        errors[i][j] = "not a complete sentence";
                    }
                }
                i++;
            }
        }

        return errors;
    }

    // formats the error messages 
    public static ArrayList<String> formatErrors(String[][] errors) {
        String errorIdentifiers = "";
        ArrayList<String> allErrors = new ArrayList<>();

        for (int i = 0; i < errors.length; i++) {
            for (int j = 0; j < errors[i].length; j++) {

                if (errors[i][j] != null) {
                    if (!(errors[i][j].equals(""))) {
                        errorIdentifiers = errorIdentifiers + errors[i][j] + "\n";

                    }
                }

            }

            if (errorIdentifiers.equals("")) {
                errorIdentifiers = "no errors found" + "\n";
            }
            allErrors.add(errorIdentifiers);
            errorIdentifiers = "";
        }

        return allErrors;
    }

    // adds "sentence" and corresponding sentence 
    // number and error message to a string
    public static String printErrors(ArrayList<String> errors) {
        int sentenceNum = 1;
        String errorMessage = "";
        for (int i = 0; i < errors.size(); i++) {

            errorMessage = errorMessage + "Sentence " + sentenceNum + ": " + errors.get(i);

            sentenceNum++;
        }

        return errorMessage;
    }

}
