
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] directors;
    
    public DirectorsFilter (String[] aDirectors) {
        directors = aDirectors;
    }
    
    public boolean satisfies (String id) {
        for (String director: directors ){
            if (MovieDatabase.getDirector(id).indexOf(director) != -1) {
                return true;
            }
        }
        return false;
    }
}
