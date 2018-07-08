
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingfile);
    }
    
    public int getMovieSize () {
        return myMovies.size();
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
        for (Movie movie: myMovies) {
            String id = movie.getID();
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating == 0) {
                continue;
            }
            else {
                Rating rating = new Rating(id, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }
    
    public String getTitle (String id) {
        String title = new String();
        for (Movie movie: myMovies) {
            if (movie.getID().equals(id)) {
                title = movie.getTitle ();
                return title;
            }
        }
        return "NO SUCH ID";
    }
    
    public String getID (String title) {
        String id = new String();
        for (Movie movie: myMovies) {
            if (movie.getTitle().equals(title)) {
                id = movie.getID();
                return id;
            }
        }
        return "NO SUCH TITLE";
    }
}