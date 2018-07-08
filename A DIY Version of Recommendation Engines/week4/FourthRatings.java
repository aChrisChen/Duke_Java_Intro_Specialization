
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    public FourthRatings () {
        //default constructor
    }
    
    private double getAverageByID (String movieID, int minimalRaters) {
        double totalRating = 0.0;
        int totalRaters = 0;
        double avgRating = 0.0;
        for (Rater rater: RaterDatabase.getRaters()) {
            for (String item: rater.getItemsRated()) {
                if (item.equals(movieID)){
                    totalRating += rater.getRating(item);
                    totalRaters ++;
                }
            }
        }
        if (totalRaters < minimalRaters) {
            return 0.0;
        }
        avgRating = totalRating/totalRaters;
        return avgRating;
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating> ();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID: myMovies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            Rating rating = new Rating(movieID, avgRating);
            avgRatings.add(rating);
        }
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) { 
        ArrayList<String> filteredIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> filteredRatings = new ArrayList<Rating> ();
        for (String movieID: filteredIDs) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            Rating rating = new Rating(movieID, avgRating);
            filteredRatings.add(rating);
        }
        return filteredRatings;
    }
    
    private int dotProduct (Rater me, Rater r) {
        int result = 0;
        for (String item: me.getItemsRated()) {
            if (r.getItemsRated().contains(item)) {
                int me_rating = (int) me.getRating(item) - 5;
                int r_rating = (int) r.getRating(item) - 5;
                result += me_rating * r_rating;
            }
        }
        return result;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        for (Rater rater: RaterDatabase.getRaters()) {
            if (rater.getID().equals(id)) {
                continue;
            }
            int similarity = dotProduct(RaterDatabase.getRater(id), rater);
            if (similarity > 0){
                similarities.add(new Rating(rater.getID(), similarity));
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarities = getSimilarities(id);
        
        // Key: Movies' IDs.  Value: RaterID and ratring value.
        HashMap<String, HashMap<String, Double>> recMap = new HashMap<String, HashMap<String, Double>> ();
        
        if (similarities.size() < numSimilarRaters) {
            numSimilarRaters = similarities.size();
        } 
        
        //initialize recMap
        for (int k=0; k<numSimilarRaters; k++) {
            String currRaterID = similarities.get(k).getItem();
            Rater currRater = RaterDatabase.getRater(currRaterID);
            ArrayList<String> ratedMovies = currRater.getItemsRated();
            for (String currMovie: ratedMovies) {
                if (!recMap.containsKey(currMovie)){
                    HashMap<String, Double> first = new HashMap<String, Double> ();
                    first.put(currRaterID, currRater.getRating(currMovie));
                    recMap.put(currMovie, first);
                } else {
                    recMap.get(currMovie).put(currRaterID, currRater.getRating(currMovie));
                }
            }
        }
        
        ArrayList<Rating> result = new ArrayList<Rating> ();
        for (String currMovie: recMap.keySet()) {
            HashMap<String, Double> currValueMap = recMap.get(currMovie);
            if (currValueMap.size() >= minimalRaters) {
                double wgt = 0;
                double total = 0;
                for (String currRaterID: currValueMap.keySet()) {
                    double currSimilarRating = 0.0;
                    for (Rating r: similarities) {
                        if (r.getItem().equals(currRaterID)) {
                            currSimilarRating = r.getValue();
                        }
                    }
                    total += currValueMap.get(currRaterID) * currSimilarRating;
                }
                double wgtAvg = total/currValueMap.size();
                result.add(new Rating(currMovie, wgtAvg));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

}   
