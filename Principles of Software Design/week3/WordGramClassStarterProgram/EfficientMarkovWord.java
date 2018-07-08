
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    private int indexOf (String[] words, WordGram target, int start) {
        for (int k=start; k < myText.length - myOrder + 1; k++) {
            WordGram current = new WordGram(words, k, myOrder);
            if (target.equals(current)) {
                return k;
            }
        }
        return -1;
    }
    
    private void buildMap () {
        for (int i = 0; i < myText.length-myOrder; i++) {
            //System.out.println("***");
            WordGram key = new WordGram(myText, i, myOrder);
            //System.out.println(key);
            if (map.containsKey(key)) {
                map.get(key).add(myText[i+myOrder]);
            } else {
                ArrayList<String> followings = new ArrayList();
                followings.add(myText[i+myOrder]);
                map.put(key, followings);
            }
        }
        WordGram key = new WordGram(myText, myText.length-myOrder, myOrder);
        map.put(key, new ArrayList<String>());
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        for (int i=0; i<key.length(); i++) {
            sb.append(key.wordAt(i));
            sb.append(" ");
        }
    
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    public void printHashMapInfo () {
        if (map.size() < 50) {
            System.out.println(map);
        }
        System.out.printf("Number of keys: %d\n", map.size());
        int max=0;
        for (WordGram key: map.keySet()){
            max = Math.max(max, map.get(key).size());
        }
        System.out.printf("Max ArrayList Size: %d\n", max);
    }
}
