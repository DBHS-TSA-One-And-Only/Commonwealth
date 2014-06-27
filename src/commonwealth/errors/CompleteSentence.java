package commonwealth.errors;

import commonwealth.members.Clause;

import java.util.ArrayList;

public class CompleteSentence {

    //checks if clauses are complete
    public static String errorOf(ArrayList<Clause> clauses) {
        String error = "not a complete sentence";
        boolean independent = false;
        for (Clause c : clauses) {
            if (c.isIndependent()) {
                independent = true;
            }
        }
        if (independent) {
            return "";
        }
        return error;

    }

}
