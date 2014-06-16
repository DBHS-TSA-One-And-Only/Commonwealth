// this class contains methods to tokenize the input sentence

package commonwealth.sentencemanager;

//not finished yet, pushed for gui testing

import java.io.*;
import java.util.ArrayList;

public class Tokenizer {

    public static void main (String[] args){

	System.out.println("Type a sentence and press 'Enter.'"); 
	
	String sentence = "";
        ArrayList<String> brokenSentence = new ArrayList<>();
	InputStreamReader input = new InputStreamReader(System.in);
	BufferedReader scanner = new BufferedReader(input);        

		try {
                    
			sentence = scanner.readLine();               //reads input
		} catch (IOException e) {
                    
			e.printStackTrace();
		}

	for (String s: sentence.split("[\\p{Punct} \\\\n\\t\\r]")){             //splits sentence into words at spaces AND punctuation!! :D 
     
            if (!(s.equals(""))){
                
                brokenSentence.add(s); 
            }
        }
        
        //for testing 
        for(String b : brokenSentence){
            System.out.println(b);
        }
    }
}
    

