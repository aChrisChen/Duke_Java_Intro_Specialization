
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    
    public CodonCount () {
        map = new HashMap<String, Integer> ();
    }
    
    public void buildCodonMap (int start, String dna) {
        map.clear();
        while (true) {
            //System.out.println(start);
            //System.out.println(dna.length());
            if (start + 3 >= dna.length()){
                break;
            }
            String currDna = dna.substring(start, start + 3);
            if (map.containsKey(currDna)){
                int currNum = map.get(currDna) + 1;
                map.put(currDna, currNum);
            } else {
                map.put(currDna, 1);
            }
            start += 3;
        }
        //for (String s : map.keySet()) {
        //    System.out.println("Key: "+s+" value: "+map.get(s));
        //} 
    }
    
    public String getMostCommonCodon () {
        String maxDna = "";
        int maxNum = 0;
        for (String codon : map.keySet()) {
            if (map.get(codon) > maxNum) {
                maxDna = codon;
                maxNum = map.get(codon);
            }
        }
        return maxDna;
    }
        
    public void printCodonCounts (int start, int end) {
        System.out.println("======================");
        System.out.println("start: "+start+" end: "+end);
        for (String codon : map.keySet()) {
            if (map.get(codon) >= start && map.get(codon) <= end) {
                System.out.println("Key: "+codon+" value: "+map.get(codon));
            }
        }
    }
    
    public void test(int frameStart,int aimShow) {
        FileResource fr = new FileResource();
        buildCodonMap(frameStart, fr.asString());
        String maxDna = getMostCommonCodon ();
        printCodonCounts(0, map.get(maxDna));
        System.out.println("Most common codon "+maxDna+" appears "+map.get(maxDna)+" times");
        System.out.println("In total, it has "+map.size()+" different codon.");
        
        for (String codon: map.keySet()) {
            if (map.get(codon) == aimShow){
                System.out.println(codon+" appears "+aimShow+" times.");
            }
        }
    }
}
