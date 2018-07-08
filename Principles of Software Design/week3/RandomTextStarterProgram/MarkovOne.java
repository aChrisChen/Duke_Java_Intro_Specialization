
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
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
}
