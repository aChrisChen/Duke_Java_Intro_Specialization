
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> map;

    public EfficientRater(String id) {
        myID = id;
        map = new HashMap<String, Rating> ();
    }

    public void addRating(String item, double rating) {
        map.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (map.containsKey(item)){
            return true;
        } else {
            return false;
        }
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (map.get(item) != null) {
            return map.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return map.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<String>(map.keySet());
    }
}
