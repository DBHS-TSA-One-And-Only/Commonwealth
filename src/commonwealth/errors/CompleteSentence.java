

package commonwealth.errors;

import commonwealth.members.Clause;

public class CompleteSentence {

	private static String error = "not a complete sentence";

	public static String errorOf(Clause c) {

		if (c.isIndependent()) {
			return "success msg goes here";
		}

		return error;

	}

}
