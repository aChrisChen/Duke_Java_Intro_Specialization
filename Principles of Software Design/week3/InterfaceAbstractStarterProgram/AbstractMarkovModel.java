
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows (String key) {
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
    abstract public String getRandomText(int numChars);

    
}
