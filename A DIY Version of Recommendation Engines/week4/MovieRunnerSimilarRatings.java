
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    private String movieFile;
    private String ratingFile;
    
    public MovieRunnerSimilarRatings () {
        //movieFile = "data/ratedmovies_short.csv";
        //ratingFile = "data/ratings_short.csv";
        movieFile = "data/ratedmoviesfull.csv";
        ratingFile = "data/ratings.csv";
    }
    
    public void printSimilarRatings () {
        System.out.println("============================");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        FourthRatings fr = new FourthRatings();
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        
        ArrayList<Rating> wgtAvgRatings = fr.getSimilarRatings("71",20,5);
        Collections.sort(wgtAvgRatings);
        for (Rating rating: wgtAvgRatings) {
            System.out.printf("%.4f %s\n",rating.getValue(),MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Totally, "+wgtAvgRatings.size()+" movies listed above.");
    }
    
    public void printSimilarRatingsByGenre () {
        System.out.println("============================");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        FourthRatings fr = new FourthRatings();
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        
        Filter f1 = new GenreFilter ("Mystery");
        
        AllFilters aFilter = new AllFilters();
        aFilter.addFilter(f1);
        
        ArrayList<String> movieIDs = MovieDatabase.filterBy(aFilter);
        ArrayList<Rating> wgtAvgRatings = fr.getSimilarRatings("964",20,5);
        Collections.sort(wgtAvgRatings);
        int num = 0;
        for (Rating rating: wgtAvgRatings) {
            if (movieIDs.contains(rating.getItem())){
                System.out.printf("%.2f %s #Genre:%s\n",rating.getValue(),
                                MovieDatabase.getTitle(rating.getItem()),
                                MovieDatabase.getGenres(rating.getItem()));
                num++;                
            }
        }
        System.out.println("Totally, "+num+" movies listed above.");
    }
    
    public void printSimilarRatingsByDirector () {
        System.out.println("============================");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        FourthRatings fr = new FourthRatings();
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        
        String[] directors =  "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh".split("\\s*,\\s*");
        Filter f1 = new DirectorsFilter (directors);
        
        AllFilters aFilter = new AllFilters();
        aFilter.addFilter(f1);
        
        ArrayList<String> movieIDs = MovieDatabase.filterBy(aFilter);
        ArrayList<Rating> wgtAvgRatings = fr.getSimilarRatings("120",10,2);
        Collections.sort(wgtAvgRatings);
        int num = 0;
        for (Rating rating: wgtAvgRatings) {
            if (movieIDs.contains(rating.getItem())){
                System.out.printf("%.2f %s\n#Directors:%s\n",rating.getValue(),
                                MovieDatabase.getTitle(rating.getItem()),
                                MovieDatabase.getDirector(rating.getItem()));
                num++;                
            }
        }
        System.out.println("Totally, "+num+" movies listed above.");
    }
    
    //printSimilarRatingsByGenreAndMinutes
    public void printSimilarRatingsByGenreAndMinutes () {
        System.out.println("============================");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        FourthRatings fr = new FourthRatings();
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        
        Filter f1 = new GenreFilter ("Drama");
        Filter f2 = new MinutesFilter(80,160);
        
        AllFilters aFilter = new AllFilters();
        aFilter.addFilter(f1);
        aFilter.addFilter(f2);
        
        ArrayList<String> movieIDs = MovieDatabase.filterBy(aFilter);
        ArrayList<Rating> wgtAvgRatings = fr.getSimilarRatings("168",10,3);
        Collections.sort(wgtAvgRatings);
        int num = 0;
        for (Rating rating: wgtAvgRatings) {
            if (movieIDs.contains(rating.getItem())){
                System.out.printf("%.2f %s #Minutes:%d #Genres:%s\n",rating.getValue(),
                                MovieDatabase.getTitle(rating.getItem()),
                                MovieDatabase.getMinutes(rating.getItem()),
                                MovieDatabase.getGenres(rating.getItem()));
                num++;                
            }
        }
        System.out.println("Totally, "+num+" movies listed above.");
    }
    
    
    //printSimilarRatingsByYearAfterAndMinutes
    public void printSimilarRatingsByYearAfterAndMinutes () {
        System.out.println("============================");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingFile);
        FourthRatings fr = new FourthRatings();
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        
        Filter f1 = new YearAfterFilter (1975);
        Filter f2 = new MinutesFilter(70,200);
        
        AllFilters aFilter = new AllFilters();
        aFilter.addFilter(f1);
        aFilter.addFilter(f2);
        
        ArrayList<String> movieIDs = MovieDatabase.filterBy(aFilter);
        ArrayList<Rating> wgtAvgRatings = fr.getSimilarRatings("314",10,5);
        Collections.sort(wgtAvgRatings);
        int num = 0;
        for (Rating rating: wgtAvgRatings) {
            if (movieIDs.contains(rating.getItem())){
                System.out.printf("%.2f %s #Year:%d #Minutes:%d\n",rating.getValue(),
                                MovieDatabase.getTitle(rating.getItem()),
                                MovieDatabase.getYear(rating.getItem()),
                                MovieDatabase.getMinutes(rating.getItem()));
                num++;                
            }
        }
        System.out.println("Totally, "+num+" movies listed above.");
    }
}
