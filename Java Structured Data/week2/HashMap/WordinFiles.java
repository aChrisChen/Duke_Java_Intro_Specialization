
/**
 * Write a description of WordinFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordinFiles {
    private HashMap<String, ArrayList<String>> map;
    
    public WordinFiles () {
        map = new HashMap<String, ArrayList<String>> ();
    }
    
    private void addWordsFromFile (File f) {
        String name = f.getName();
        FileResource fr = new FileResource (f);
        
        for (String oldWord : fr.words()) {
            // String word = new String(oldWord.replaceAll("[^a-zA-Z]","").toLowerCase());
            String word = new String(oldWord);
            if (map.containsKey(word) == true) {
                if (!map.get(word).contains(name)){
                    map.get(word).add(name);
                }
            } else {
                ArrayList<String> fileNames = new ArrayList();
                fileNames.add(name);
                map.put(word, fileNames);
            }
        }
    }
    
    public void buildWordFileMap () {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber () {
        int maxNumber = 0;
        String maxWord = "";
        for (String word : map.keySet()) {
            if (map.get(word).size() > maxNumber) {
                maxNumber = map.get(word).size();
                maxWord = word;
            }
        }
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : map.keySet()) {
            if (map.get(word).size() == number) {
                words.add(word);
            }
        }
        return words;
    }
    
    public void printFilesIn (String word) {
        System.out.println("*********");
        System.out.println("the word "+word+" are in following files: ");
        for (String fileName : map.get(word)) {
            System.out.print(fileName+" || ");
        }
        System.out.println();
    }
    
    public void printMap() {
        int i =0;
        for (String word : map.keySet()){
            System.out.print(word+" in "+map.get(word).size()+" files: ");
            for (String fileName : map.get(word)) {
                System.out.print(fileName+" || ");
            }
            System.out.println();
            i++;
            if (i > 10) {
                break;
            }
        }
       
    }
    
    public void tester () {
        System.out.println("==========");
        buildWordFileMap();
        
        int maxNum = maxNumber();
        System.out.println(maxNum);
        int i = 0;
        int j = 0;
        for (String word : map.keySet()) {
            if (map.get(word).size() == 7){
                printFilesIn (word);
                i ++;
            }
            j++;
        }
        System.out.println("It has "+i+" words appearing in 7 files");
        System.out.println("All files has "+j+" words.");
        System.out.println("A word at most in "+maxNum+" files.");
        printFilesIn("tree");
        //printMap();
    }
}
