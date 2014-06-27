// this is for creating Sentence objects
package commonwealth.members;

import commonwealth.sentencemanager.Identifier;
import commonwealth.sentencemanager.Splitter;
import java.util.ArrayList;

public class Sentence {

    private ArrayList<Clause> clauses = new ArrayList<>();      //an arraylist of all the clauses in the sentence
    private ArrayList<String> tokenizedSentence, charIdentifier;
    private ArrayList<ArrayList<String>> splitStrings;
    Splitter splitter = new Splitter();
    Identifier identifier = new Identifier();

    // sets up tokenizedSentence and charIdentifier arrays and
    // splits sentence obj into clauses via initializeClauses method
    public Sentence(ArrayList<String> input, ArrayList<String> posTags) {
        tokenizedSentence = input;
        charIdentifier = posTags;
        initializeClauses();

    }
    
    //creates clauses based off the sentence object 
    public void initializeClauses() {
        splitStrings = splitter.splitClauses(this);

        for (int i = 0; i < splitStrings.size(); i += 2) {
            if (identifier.isClause(splitStrings.get(i), splitStrings.get(i + 1))) {
                ArrayList<ArrayList<String>> clauseBundle = new ArrayList<>();

                clauseBundle.add(identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1)).get(0)); // subjects found in clause
                clauseBundle.add(identifier.subjectsOf(splitStrings.get(i), splitStrings.get(i + 1)).get(1)); // subject identifiers
                clauseBundle.add(identifier.actionsOf(splitStrings.get(i), splitStrings.get(i + 1)).get(0));  // actions found in clause
                clauseBundle.add(identifier.actionsOf(splitStrings.get(i), splitStrings.get(i + 1)).get(1)); //action identifiers 
                clauseBundle.add(splitStrings.get(i)); 														  // clause itself
                clauses.add(new Clause(clauseBundle)); 														  // create clause, add to arraylist of clauses
            }
        }
    }

    public ArrayList<Clause> getClauses() {

        return clauses;
    }

    public ArrayList<String> getSentence() {

        return tokenizedSentence;
    }

    public ArrayList<String> getPosTags() {

        return charIdentifier;
    }

}
