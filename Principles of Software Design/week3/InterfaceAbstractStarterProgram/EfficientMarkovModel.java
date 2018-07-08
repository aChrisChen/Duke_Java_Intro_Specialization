
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int orderIn) {
        myRandom = new Random();
        order = orderIn;
        map = new  HashMap<String, ArrayList<String>>();
    }
    
    public String toString () {
        return "MarkovModel of order " + order + ".";
    }
    
    public void setTraining (String s) {
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap () {
        for (int i=0; i < myText.length() - order; i++){
            String key = myText.substring(i,i+order);
            String following = myText.substring(i+order, i+order+1);
            if(map.containsKey(key)){
                map.get(key).add(following);
            } else {
                ArrayList<String> followings = new ArrayList<String>();
                followings.add(following);
                map.put(key, followings);
            }
        }
        String key = myText.substring(myText.length()-order,myText.length());
        ArrayList<String> followings = new ArrayList<String>();
        map.put(key, followings);
    }
    
    public ArrayList<String> getFollows (String key) {
        return map.get(key);
        
    }
    
    public String getRandomText(int numChars) {
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index, index+order);
        sb.append(key);
        for(int k=0; k < numChars - order; k++){
            //System.out.println("[[[[[["+key+"]]]]]");
            ArrayList<String> follows = getFollows(key);
            
            //System.out.println("*****"+follows.size()+"******");
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
    
    public void printHashMapInfo () {
        if (map.size() < 50) {
            System.out.println(map);
        }
        System.out.printf("Number of keys: %d\n", map.size());
        int max=0;
        for (String key: map.keySet()){
            max = Math.max(max, map.get(key).size());
        }
        System.out.printf("Max ArrayList Size: %d\n", max);
    }
    
}
