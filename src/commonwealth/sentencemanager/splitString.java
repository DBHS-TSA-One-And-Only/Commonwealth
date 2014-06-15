/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commonwealth.sentencemanager;

/**
 *
 * @author forest
 */

//not finished yet, pushed for gui testing

import java.io.*;
import java.util.ArrayList;

public class splitString {

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
    }
}
    

