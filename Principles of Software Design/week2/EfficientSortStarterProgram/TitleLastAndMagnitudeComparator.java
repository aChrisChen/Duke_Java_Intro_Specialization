
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int compareResult = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1).
            compareTo(q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1));
        if (compareResult == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return compareResult;
    }
}
