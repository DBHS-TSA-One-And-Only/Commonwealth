

package commonwealth.errors;

import commonwealth.members.Clause;

import java.util.ArrayList;

public class CompleteSentence {

	private static String error = "not a complete sentence";

	public static String errorOf(ArrayList<Clause> clauses) {
		boolean independent = false;
		for(Clause c: clauses){
			if (c.isIndependent()) {
				independent = true;
			}
		}
		if(independent){
			return "";
		}
		return error;

	}

}
