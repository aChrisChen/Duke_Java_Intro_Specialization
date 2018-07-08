
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter (int sMinutes, int lMinutes) {
        minMinutes = sMinutes;
        maxMinutes = lMinutes;
    }
    
    public boolean satisfies (String id) {
        if ( MovieDatabase.getMinutes(id) >= minMinutes
                    && MovieDatabase.getMinutes(id) <= maxMinutes) {
            return true;
        }
        return false;
    }
}
