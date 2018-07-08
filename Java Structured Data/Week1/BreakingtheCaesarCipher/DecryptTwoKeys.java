
/**
 * Write a description of DecryptTwoKeys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class DecryptTwoKeys {
    public String encrypt (String input, int key) {
        System.out.println("==============");
        StringBuilder encrypted = new StringBuilder(input);
        String upAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowAlphabet = upAlphabet.toLowerCase();
        String alphabet = upAlphabet + lowAlphabet;
        String shiftedAlphabet = upAlphabet.substring(key) + upAlphabet.substring(0,key)
            + lowAlphabet.substring(key) + lowAlphabet.substring(0, key);
        //System.out.println(alphabet);
        //System.out.println(shiftedAlphabet);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }

    public String encryptTwoKeys (String input, int key1, int key2) {
        System.out.println("==============");
        String encrypted1 = encrypt(input, key1);
        String encrypted2 = encrypt(input, key2);
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            if ((i + 1) % 2 == 1){
                char currChar = encrypted1.charAt(i);
                encrypted.setCharAt(i, currChar);
            }
            if ((i + 1) % 2 == 0){
                char currChar = encrypted2.charAt(i);
                encrypted.setCharAt(i, currChar);
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    
    public String singleBreaker (String encrypted) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = Character.toLowerCase(encrypted.charAt(i));
            int position = alphabet.indexOf(currChar);
            if (position != -1){
                counts[position] += 1;
            }
        }
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxValue) {
                maxIndex = i;
                maxValue = counts[i];
            }
        }
        System.out.println("NOT KEY!!this is the index of moved string which is shifted");
        System.out.println("111xxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(maxIndex);
        System.out.println("111xxxxxxxxxxxxxxxxxxxxxxxx");
        String msg;
        if (maxIndex > 4){
            msg = encrypt(encrypted, 26-(maxIndex - 4));
        } else {
            msg = encrypt(encrypted, 4 - maxIndex);
        }
        return msg;
    }
    
    public String halfOfString (String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (i % 2 == start) {
                half.append(message.charAt(i));
            }
        }
        return half.toString();
    }
    
    public String CaesarBreaker (String encrypted) {
        String source1 = halfOfString(encrypted, 0);
        String source2 = halfOfString(encrypted, 1);

        String result1 = singleBreaker(source1.toString());
        String result2 = singleBreaker(source2.toString());
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                result.append(result1.charAt(i/2));
            }
            if (i % 2 == 1) {
                result.append(result2.charAt(i/2));
            } 
        }
        System.out.println(result.toString());
        return result.toString();
    }
    
    public void testCaesarBreaker () {
        FileResource fr = new FileResource();
        String result = CaesarBreaker(fr.asString());
        System.out.println("++++++++++");
        System.out.println(result);
    }
    
}
