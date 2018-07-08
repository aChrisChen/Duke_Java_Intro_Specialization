
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int rank;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        rank = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
   
    public ArrayList<String> getFollows (String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int startIndex = 0;
        while (true) {
            //System.out.println(startIndex);
            int pos = myText.indexOf(key, startIndex);
            if (pos == -1 || pos+key.length() >= myText.length()) {
                break;
            }
            follows.add(myText.substring(pos+key.length(), pos+key.length()+1));
            startIndex = pos+1;
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-rank);
        String key = myText.substring(index, index+rank);
        sb.append(key);
        for(int k=0; k < numChars - rank; k++){
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
}
