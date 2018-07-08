
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    private String movieFile;
    private String ratingFile;
    
    public MovieRunnerWithFilters () {
        //movieFile = "data/ratedmovies_short.csv";
        //ratingFile = "data/ratings_short.csv";
        movieFile = "data/ratedmoviesfull.csv";
        ratingFile = "data/ratings.csv";
    }
    
    public void printAverageRatings () {
        System.out.println("============================");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        ArrayList<Rating> avgRatings = tr.getAverageRatings(35);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(avgRatings);
        for (Rating rating: avgRatings) {
            System.out.printf("%.4f %s\n",rating.getValue(),MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+avgRatings.size()+" movies listed above.");
    }
    
    public void printAverageRatingsByYear () {
        System.out.println("============================");
        int minimalRaters = 20;
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> filteredRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(filteredRatings);
        for (Rating rating: filteredRatings) {
            System.out.printf("%.4f %d %s\n",rating.getValue(), 
                                MovieDatabase.getYear(rating.getItem()), 
                                MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+filteredRatings.size()+" movies listed above.");
    }
    
    public void printAverageRatingsByGenre () {
        System.out.println("============================");
        int minimalRaters = 20;
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        Filter f = new GenreFilter ("Comedy");
        ArrayList<Rating> filteredRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(filteredRatings);
        for (Rating rating: filteredRatings) {
            System.out.printf("%.4f %s %s\n",rating.getValue(), 
                                MovieDatabase.getGenres(rating.getItem()), 
                                MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+filteredRatings.size()+" movies listed above.");
    }
    
    public void printAverageRatingsByMinuets () {
        System.out.println("============================");
        int minimalRaters = 5;
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        Filter f = new MinutesFilter (105, 135);
        ArrayList<Rating> filteredRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(filteredRatings);
        for (Rating rating: filteredRatings) {
            System.out.printf("%.4f %s %s\n",rating.getValue(), 
                                MovieDatabase.getMinutes(rating.getItem()), 
                                MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+filteredRatings.size()+" movies listed above.");
    }
    
    public void printAverageRatingsByDirectors () {
        System.out.println("============================");
        int minimalRaters = 4;
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        String[] directors =  "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack".split("\\s*,\\s*");
        Filter f = new DirectorsFilter (directors);
        ArrayList<Rating> filteredRatings = tr.getAverageRatingsByFilter(minimalRaters, f);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(filteredRatings);
        for (Rating rating: filteredRatings) {
            System.out.printf("%.4f %s %s\n",rating.getValue(), 
                                MovieDatabase.getDirector(rating.getItem()), 
                                MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+filteredRatings.size()+" movies listed above.");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes () {
        System.out.println("============================");
        int minimalRaters = 3;
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        String[] directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack".split("\\s*,\\s*");
        Filter f1 = new DirectorsFilter (directors);
        Filter f2 = new MinutesFilter (90, 180);
        
        AllFilters aFilter = new AllFilters();
        aFilter.addFilter(f1);
        aFilter.addFilter(f2);
        
        ArrayList<Rating> filteredRatings = tr.getAverageRatingsByFilter(minimalRaters, aFilter);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(filteredRatings);
        for (Rating rating: filteredRatings) {
            System.out.printf("%.4f Time: %d %s\n %s\n",rating.getValue(), 
                                MovieDatabase.getMinutes(rating.getItem()),
                                MovieDatabase.getTitle(rating.getItem()),
                                MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("Totally, "+filteredRatings.size()+" movies listed above.");
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        System.out.println("============================");
        int minimalRaters = 8;
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingFile);
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        
        Filter f1 = new YearAfterFilter (1990);
        Filter f2 = new GenreFilter ("Drama");
        
        AllFilters aFilter = new AllFilters();
        aFilter.addFilter(f1);
        aFilter.addFilter(f2);
        
        ArrayList<Rating> filteredRatings = tr.getAverageRatingsByFilter(minimalRaters, aFilter);
        //System.out.println("***"+sr.getAverageByID("1798709",1));
        Collections.sort(filteredRatings);
        for (Rating rating: filteredRatings) {
            System.out.printf("%.4f Time: %d %s\n %s\n",rating.getValue(), 
                                MovieDatabase.getYear(rating.getItem()),
                                MovieDatabase.getTitle(rating.getItem()),
                                MovieDatabase.getGenres(rating.getItem()));
        }
        System.out.println("Totally, "+filteredRatings.size()+" movies listed above.");
    }
}
