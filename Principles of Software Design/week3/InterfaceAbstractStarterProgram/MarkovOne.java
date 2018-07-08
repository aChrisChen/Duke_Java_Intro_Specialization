
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    private int order;
    
    public MarkovOne() {
        myRandom = new Random();
        order = 1;
    }
    

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String key = Character.toString(myText.charAt(myRandom.nextInt(myText.length()-1)));
        sb.append(key);
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            int index = myRandom.nextInt(follows.size());
            key = follows.get(index);
            sb.append(key);
        }
        
        return sb.toString();
    }
    
    public String toString() {
         return "Markov with order "+ order;
    }
}
