// this class contains methods to split the tokenized sentence array into clauses

package commonwealth.sentencemanager;

import commonwealth.members.Sentence;
import java.util.ArrayList;

public class Splitter {

	public ArrayList<String> tokenizedSentence = new ArrayList<>(), charIdentifier = new ArrayList<>();
	public ArrayList<ArrayList<String>> clauses = new ArrayList<>();

	
	// this method splits the tokenized string sentence into clauses based on
	// clause commas, semicolons, or colons
	// returns multiple arraylists in an arraylist of arraylists
	// each arraylist in the returned arraylist will be a POTENTIAL clause
	public ArrayList<ArrayList<String>> splitClauses(Sentence input) {
		tokenizedSentence = input.getSentence();
		charIdentifier= input.getPosTags();
		int previousIndex = 0;

		for (int index = 0; index < tokenizedSentence.size(); index++) {
			
			if ((tokenizedSentence.get(index).matches("[;:]") || (index == tokenizedSentence.size()-1))||(tokenizedSentence.get(index).equals(",")&&(tokenizedSentence.get(index+2).matches("[for|and|nor|but|or|yet|so]")))) {
						
				ArrayList<String> clauseHolder = new ArrayList<>(), identifierHolder = new ArrayList<>();
				for (int i = previousIndex; i <= index; i++) {
					clauseHolder.add(tokenizedSentence.get(i));
					identifierHolder.add(charIdentifier.get(i));
				}
				clauses.add(clauseHolder);
				clauses.add(identifierHolder);
				previousIndex = index+1;
					
			}
		}
		return clauses;
	}
	
	
	//splits userinput into multiple sentences at periods,
	//exclamation marks, or question marks, returns
	//arraylist of strings that are sentences
	public ArrayList<String> splitInput(String input){
		boolean quote = false;
		int index=0;
		ArrayList<String> sentences = new ArrayList<>();
		
		for(int i = 0; i < input.length(); i ++){
			if(input.substring(i,i+1).equals("\"")){
				quote = !quote;
			}
			if((input.substring(i, i +1).matches("[.!?]") || (i == input.length()-1))){
				//if(input.length()>3){
					if(!quote && !(input.substring(i-2, i).matches("Mr|Ms|Dr|St|Jr|Sr|co")) && !(input.substring(i-3, i).matches("Mrs|etc|Gen|inc"))){
						sentences.add(input.substring(index, i+1));
						index=i+2;
				//}
				}
				
			}
		}
		
		return sentences;
	}

}
