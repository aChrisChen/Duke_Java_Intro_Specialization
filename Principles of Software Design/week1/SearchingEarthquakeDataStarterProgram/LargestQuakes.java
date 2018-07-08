
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    
    private String sourceData = "data/nov20quakedata.atom";
    
    public void findLargestQuakes () {
        System.out.println("=======start=======");
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String source = sourceData;
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        
        ArrayList<QuakeEntry> answer = getLargest(list, 50);
        for(int k=0; k < answer.size(); k++){
            QuakeEntry entry = answer.get(k);
            System.out.println(entry);
        }
        System.out.println("number found: "+answer.size());
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double maxMag = 0;
        int maxIndex = 0;
        for (int i=0; i<data.size(); i++) {
            QuakeEntry qe = data.get(i);
            double currMag = qe.getMagnitude();
            if (currMag > maxMag){
                maxIndex = i;
                maxMag = currMag;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry> ();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry> (quakeData);
        for (int i=0; i< howMany; i++) {
            QuakeEntry currLargest = copy.get(indexOfLargest(copy));
            answer.add(currLargest);
            copy.remove(currLargest);
        }
        return answer;
    }
}

