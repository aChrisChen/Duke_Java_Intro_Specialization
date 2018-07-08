
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingfile);
    }
    
    public int getRaterSize () {
        return myRaters.size();
    }
    
    private double getAverageByID (String id, int minimalRaters) {
        double totalRating = 0.0;
        int totalRaters = 0;
        double avgRating = 0.0;
        for (Rater rater: myRaters) {
            for (String item: rater.getItemsRated()) {
                if (item.equals(id)){
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
            if (avgRating == 0) {
                continue;
            }
            else {
                Rating rating = new Rating(movieID, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) { 
        ArrayList<String> filteredIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> filteredRatings = new ArrayList<Rating> ();
        for (String movieID: filteredIDs) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating == 0) {
                continue;
            }
            else {
                Rating rating = new Rating(movieID, avgRating);
                filteredRatings.add(rating);
            }
        }
        return filteredRatings;
    }
}
