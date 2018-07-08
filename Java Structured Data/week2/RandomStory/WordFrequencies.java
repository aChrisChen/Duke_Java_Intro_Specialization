
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies () {
        myWords = new ArrayList<String> ();
        myFreqs = new ArrayList<Integer> ();
    }
    
    public void findUnique () {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int currFreq = myFreqs.get(index);
                myFreqs.set(index, currFreq + 1);
            }
        }
    }
    
    public int findMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            System.out.println(myWords.get(i)+" freq: "+myFreqs.get(i));
            if (myFreqs.get(i) > max) {
                maxIndex = i;
                max = myFreqs.get(i);
            }
        }
        return maxIndex;
    }
    
    public void tester () {
        System.out.println("=========================");
        findUnique();
        int index = findMax();
        System.out.println("# unique words: "+myWords.size());
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }
    
    
    
}
