
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int order;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        order = n;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index, index+order);
        sb.append(key);
        for(int k=0; k < numChars - order; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
            //System.out.println(key+"******"+follows);
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "Markov with order "+ order;
    }
}
