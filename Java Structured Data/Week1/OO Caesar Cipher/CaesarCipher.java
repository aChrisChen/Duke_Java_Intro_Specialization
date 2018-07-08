
/**
 * Write a description of OneKey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String upAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lowAlphabet = upAlphabet.toLowerCase();
    private String alphabet = upAlphabet + lowAlphabet;
    private String shiftedAlphabet = "";
    
    public CaesarCipher (int key) {
        shiftedAlphabet = upAlphabet.substring(key) + upAlphabet.substring(0,key)
            + lowAlphabet.substring(key) + lowAlphabet.substring(0, key);
    }
    
    public String encrypt (String input) {
        System.out.println("==============");
        StringBuilder encrypted = new StringBuilder(input);
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
    
    public String decrypt (String encrypted) {
        StringBuilder decrypted = new StringBuilder(encrypted);
        for (int i = 0; i < decrypted.length(); i++) {
            char currChar = decrypted.charAt(i);
            int idx = shiftedAlphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = alphabet.charAt(idx);
                decrypted.setCharAt(i, newChar);
            }
        }

        return decrypted.toString();
    }
    
    public int[] countLetters (String input) {
        int[] values = new int[26];
        for (int i = 0; i < input.length(); i++) {
            char currChar = Character.toUpperCase(input.charAt(i));
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                values[idx] += 1;
            }
        }
        return values;
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
    
    public void TestCaesarCipher () {
        
    }
    
}
