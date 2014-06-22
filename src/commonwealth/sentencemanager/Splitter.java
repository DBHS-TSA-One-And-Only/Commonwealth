// this class contains methods to split the tokenized sentence array into clauses

package commonwealth.sentencemanager;

import java.util.ArrayList;

public class Splitter {

	public static ArrayList<String> tokenizedSentence = new ArrayList<>(), charIdentifier = new ArrayList<>();
	public static ArrayList<ArrayList<String>> clauses = new ArrayList<>(), charIdentifiers = new ArrayList<>();
	
	// this method splits the tokenized string sentence into clauses based on
	// clause conjunction keywords or commas
	// returns multiple arraylists in an arraylist of arraylists
	// each arraylist in the returned arraylist will be a POTENTIAL clause
	public static ArrayList<ArrayList<String>> splitClauses(
			ArrayList<String> sentence, ArrayList<String> characterType) {
		tokenizedSentence = sentence;
		charIdentifier= characterType;
		int previousIndex = 0;

		for (int index = 0; index < tokenizedSentence.size(); index++) {
			if (tokenizedSentence.get(index).matches("[,;:]")) {
				ArrayList<String> clauseHolder = new ArrayList<>(), identifierHolder = new ArrayList<>();
				for (int i = previousIndex; i <= index; i++) {
					clauseHolder.add(tokenizedSentence.get(i));
					identifierHolder.add(charIdentifier.get(i));
				}
				clauses.add(clauseHolder);
				charIdentifiers.add(identifierHolder);
				previousIndex = index+1;
			}
		}
		return clauses;
	}
        
        //not quite finished but good enough for now 
        public ArrayList<String> splitInput(String input){
		boolean quote = false;
		int index=0;
		ArrayList<String> sentences = new ArrayList<String>();
		
		for(int i = 0; i < input.length(); i ++){
			if(input.substring(i,i+1).equals("\"")){
				quote = !quote;
			}
			if((input.substring(i, i +1).matches("[.!?]") || (i == input.length()-1))){
				if(!quote && !(input.substring(i-2, i).matches("Mr|Ms|Dr|St|Jr|Sr")) && !(input.substring(i-3, i).matches("Mrs|etc|Gen"))){
					sentences.add(input.substring(index, i+1));
					index=i+2;
				}
			}
		}
		
		return sentences;
	}

}
