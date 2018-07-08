import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        int currChar = whichSlice;
        while (currChar < message.length()) {
            slice.append(message.charAt(currChar));
            currChar +=totalSlices;
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] dkeys = new int[klength];
        String[] slices = new String[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int k = 0; k < klength; k++) {
            slices[k] = sliceString(encrypted, k, klength);
            dkeys[k] = cc.getKey(slices[k]);
        }
        return dkeys;
    }

    public void  breakVigenere () {
        System.out.println("=============start=============");
        // initialize encrypted message
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //initialize the languages HashMap
        DirectoryResource dr = new DirectoryResource();
        String[] langNames = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        int i = 0;
        for (File f : dr.selectedFiles()) {
            String langName = langNames[i++];
            FileResource frDic = new FileResource("Data/dictionaries/"+langName);
            HashSet<String> dictionary = readDictionary(frDic);
            languages.put(langName, dictionary);
        }
        //solve the problem
        String answer = breakForAllLangs(encrypted, languages);
        System.out.println(answer.substring(0,100));
        System.out.println("=============finish=============");
    }
    
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dictionary = new HashSet<String> ();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.toLowerCase().split("\\W+");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCounts = 0;
        int bestK = 0;
        int[] bestKeys = new int[100];
        String bestMessage = new String();
        int specialcount38 = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        
        for (int klength = 1; klength < 100; klength++) {
            int[] dkeys = tryKeyLength(encrypted, klength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(dkeys);
            String message = vc.decrypt(encrypted);
            if (countWords(message, dictionary) > maxCounts) {
                maxCounts = countWords(message, dictionary);
                bestK = klength;
                bestKeys = dkeys;
                bestMessage = message;
            }
            if (klength == 38){
                specialcount38 = countWords(message, dictionary);
            }
        }
        return bestMessage;
    }
    
    public int[] keyBreakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCounts = 0;
        int bestK = 0;
        int[] bestKeys = new int[100];
        String bestMessage = new String();
        int specialcount38 = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        
        for (int klength = 1; klength < 100; klength++) {
            int[] dkeys = tryKeyLength(encrypted, klength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(dkeys);
            String message = vc.decrypt(encrypted);
            if (countWords(message, dictionary) > maxCounts) {
                maxCounts = countWords(message, dictionary);
                bestK = klength;
                bestKeys = dkeys;
                bestMessage = message;
            }
            if (klength == 38){
                specialcount38 = countWords(message, dictionary);
            }
        }
        return bestKeys;
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        int maxIndex = 0;
        for (String word : dictionary) {
            word = word.toLowerCase();
            for (int i=0; i<word.length(); i++) {
                char letter = word.charAt(i);
                int pos = alphabet.indexOf(letter);
                if (pos == -1){
                    //System.out.print(letter);
                    continue;
                }
                count[pos]++;
            }
        }
        for (int i=0; i<count.length; i++) {
            maxIndex = count[i] > count[maxIndex] ? i : maxIndex;
        }
        return alphabet.charAt(maxIndex);
    }
    
    public String breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        int bestCount = 0;
        String bestLang = new String();
        String answer = new String();
        int[] bestKeys = new int[10];
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String message = breakForLanguage(encrypted, dictionary);
            int count = countWords(message, dictionary);
        
            if (count > bestCount) {
                bestCount = count;
                bestLang = language;
                answer = message;
                bestKeys = keyBreakForLanguage(encrypted, dictionary);
            }
        }

        System.out.println("Valid words: "+bestCount);
        System.out.println("Best language: "+bestLang);
        System.out.println("Keys with length "+bestKeys.length+" :");
        for (int i = 0; i < bestKeys.length; i++) {
            System.out.print(bestKeys[i]+",");
        }
        System.out.println();
        return answer;
    }
}
