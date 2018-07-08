
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
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
    
    public void test() {
        System.out.println("==============");
        FileResource fr = new FileResource();
        String msg = fr.asString();
        int key = 23;
        String encrypted = encrypt(msg, key);
        System.out.println("key is " + key + "\n" + encrypted);
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
}
