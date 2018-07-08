
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public int [] countWordLengths (FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int k = word.length();
            if (!Character.isLetter(word.charAt(0))) {
                k -= 1;
            }
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                k -= 1;
            }
            
            if (k!=-1 && k < counts.length) {
                counts[k] += 1;
            }
        }
        return counts;
    }
    
    public void testCountWordLengths () {
        System.out.print("======================");
        FileResource fr = new FileResource();
        int [] counts = new int [31];
        counts = countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i+" length: "+counts[i]);
        }
        int mostWords = indexOfMax(counts);
        System.out.println("Most words are of length :"+mostWords);
    }
    
    public int indexOfMax (int[] values) {
        int position = 0;
        int maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (maxValue == 0) {
                position = i;
                maxValue = values[i];
            }
            if (values[i] > maxValue) {
                position = i;
                maxValue = values[i];
            }
        }
        return position;
    }
}
