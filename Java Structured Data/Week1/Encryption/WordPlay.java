
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel ( char ch) {
        String vowel = "aeiou";
        char lowCh = (char) (ch ^ 0x20); // to lower case
        
        if (vowel.contains(String.valueOf(lowCh))){
            return true;
        }
        return false;
    }
    
    public String replaceVowels (String phrase, char ch) {
        StringBuilder encrypted = new StringBuilder(phrase);
        for (int i = 0; i < encrypted.length(); i++) {
            char currCh = encrypted.charAt(i);
            if (isVowel(currCh)) {
                encrypted.setCharAt(i, ch);
            }
        }
        return encrypted.toString();
    }
    
    public String emphasize (String phrase, char ch) {
        StringBuilder emphasized = new StringBuilder(phrase);
        for (int i = 0; i < emphasized.length(); i++) {
            char currCh = emphasized.charAt(i);
            if (currCh == ch){
                if ((i + 1) % 2 == 1){
                    emphasized.setCharAt(i, '*');
                }
                if ((i + 1) % 2 == 0) {
                    emphasized.setCharAt(i, '+');
                }
            }
        }
        return emphasized.toString();
    }
}
