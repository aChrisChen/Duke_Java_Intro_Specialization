
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies (String file){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record: parser) {
            Movie currMovie = new Movie (
                                    record.get("id"),
                                    record.get("title"),
                                    record.get("year"),
                                    record.get("genre"),
                                    record.get("director"),
                                    record.get("country"),
                                    record.get("poster"),
                                    Integer.parseInt(record.get("minutes")));
            movies.add(currMovie);
        }
        return movies;
    }
    
    public void testLoadMovies () {
        System.out.println("=====================");
        //String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies =  loadMovies(filename);
        System.out.println("Number of movies: "+movies.size());
        
        int count_genre = 0;
        int count_length = 0;
        for (Movie movie: movies) {
            if (movie.getGenres().indexOf("Comedy") != -1 ){
                count_genre++;
            }
            if (movie.getMinutes() > 150) {
                count_length++;
            }
        }
        System.out.println("Num of Comedy: "+count_genre);
        System.out.println("Num of length greater than 150 mins: "+count_length);
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Movie movie: movies) {
            String[] directors = movie.getDirector().split("\\s*,\\s*");
            for (String director: directors) {
                if (!map.containsKey(director)) {
                    map.put(director,1);
                } else {
                    int currNum = map.get(director);
                    currNum++;
                    map.put(director, currNum);
                }
            }
        }
        String maxDirector = new String();
        int maxNum = 0;
        for (String director: map.keySet()) {
            if (map.get(director) > maxNum) {
                maxDirector = director;
                maxNum = map.get(director);
            }
        }
        System.out.println("Director with most movies: "+maxDirector+" with "+maxNum+" movies ");
    }
    
    public ArrayList<Rater> loadRaters(String file){
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            int index = ids.indexOf(record.get("rater_id"));
            if (index == -1) {
                Rater currRater = new EfficientRater(record.get("rater_id"));
                currRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                ids.add(record.get("rater_id"));
                raters.add(currRater);
            } else {
                Rater currRater = raters.get(index);
                currRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raters.set(index, currRater);
            }
        }
        
        return raters;
    }
    
    public void testLoadRaters () {
        System.out.println("=====================");
        //String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        ArrayList<Rater> raters =  loadRaters(filename);
        System.out.println("Number of Raters: "+raters.size());
        
        String id = "193";
        for (Rater rater: raters) {
            if (rater.getID().equals(id)){
                System.out.println("Rater "+id+" has "+rater.numRatings()+" ratings.");
                break;
            }
        }
        
        int maxRating = 0;
        int maxRater = 0;
        ArrayList<String> maxRaters = new ArrayList<String>();
        for (Rater rater: raters) {
            if (rater.numRatings() > maxRating) {
                maxRating = rater.numRatings();
            }
        }
        for (Rater rater: raters) {
            if (rater.numRatings() == maxRating) {
                maxRater++;
                maxRaters.add(rater.getID());
            }
        }
        System.out.println("A rater has at most "+maxRating+" ratings.");
        System.out.println("These "+maxRater+" Rating includes "+maxRaters);
        
        String movieId = "1798709";
        int countMovieId = 0;
        for (Rater rater: raters) {
            for (String item: rater.getItemsRated()) {
                if (item.equals(movieId)) {
                    countMovieId ++;
                }
            }
        }
        System.out.println("Movie "+movieId+" has "+countMovieId+" ratings.");
        
        ArrayList<String> AllMovieRated = new ArrayList<String>();
        for (Rater rater: raters) {
            for (String item: rater.getItemsRated()) {
                if (!AllMovieRated.contains(item)) {
                    AllMovieRated.add(item);
                }
            }
        }
        System.out.println("There are "+AllMovieRated.size()+" movies being rated by all raters.");
    }
}
