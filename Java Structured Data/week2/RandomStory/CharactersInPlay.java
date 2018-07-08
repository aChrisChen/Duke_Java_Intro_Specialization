
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    private ArrayList<String> newNames;
    private ArrayList<Integer> newFreqs;

    
    public  CharactersInPlay () {
        names = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
        newNames = new ArrayList<String>();
        newFreqs = new ArrayList<Integer>();
    }

    private void update (String person) {
        person = person.toUpperCase();
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            freqs.add(1);
        } else {
            int freq = freqs.get(index);
            freqs.set(index, freq + 1);
        }
       
    }
    
    public void findAllCharacters () {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int index = line.indexOf(".");
            if (index > 0) {
                String person = line.substring(0, index).trim();
                update(person);
            }
        }
    }

    public int findMax() {
        int max = freqs.get(0);
        int maxIndex = 0;
        for (int i = 0; i < freqs.size(); i++) {
            System.out.println(names.get(i)+" freq: "+names.get(i));
            if (freqs.get(i) > max) {
                maxIndex = i;
                max = freqs.get(i);
            }
        }
        return maxIndex;
    }
    
    public void tester () {
        names.clear();
        freqs.clear();
        System.out.println("====================");
        findAllCharacters();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int freq = freqs.get(i);
            if (freq >= 50 && freq <= 100) {
                newNames.add(names.get(i));
                newFreqs.add(freqs.get(i));
                //System.out.println("xxxxxxChracter "+name+" with freq "+freq);
            }
        }
        System.out.println("");
        int index = findMax();
        System.out.println("# unique chracters: "+newNames.size());
        for (int i = 0; i < newNames.size(); i++){
             System.out.println("One of the role with 50~100: "+i+" "+newNames.get(i)+" "+newFreqs.get(i));
        }
       
        System.out.println("max chracters/freq: "+names.get(index)+" "+freqs.get(index));
    }
}
