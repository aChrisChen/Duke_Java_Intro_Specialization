
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage { 
    private String movieFile;
    private String ratingFile;
    
    public MovieRunnerAverage () {
        //movieFile = "data/ratedmovies_short.csv";
        //ratingFile = "data/ratings_short.csv";
        movieFile = "data/ratedmoviesfull.csv";
        ratingFile = "data/ratings.csv";
    }
    
    public void printAverageRatings () {
        System.out.println("============================");
        SecondRatings sr = new SecondRatings(movieFile, ratingFile);
        System.out.println("Total movies: "+sr.getMovieSize());
        System.out.println("Total raters: "+sr.getRaterSize());
        
        ArrayList<Rating> avgRatings = sr.getAverageRatings(12);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(avgRatings);
        for (Rating rating: avgRatings) {
            System.out.printf("%.4f %s\n",rating.getValue(),sr.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+avgRatings.size()+" movies listed above.");
    }
    
    public void getAverageRatingOneMovie () {
        System.out.println("============================");
        SecondRatings sr = new SecondRatings(movieFile, ratingFile);
        ArrayList<Rating> avgRatings = sr.getAverageRatings(0);
        String name = "Vacation";
        String id = sr.getID(name);
        for (Rating rating: avgRatings) {
            if (rating.getItem().equals(id)){
                System.out.printf("%.4f %s\n",rating.getValue(),sr.getTitle(rating.getItem()));
            }
        }
        
    }
    
    
}
