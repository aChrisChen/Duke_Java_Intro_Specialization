
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private int key1 = 0;
    private int key2 = 0;
    CaesarCipher half1 = null;
    CaesarCipher half2 = null;
    
    public CaesarCipherTwo (int startKey1, int startKey2) {
        key1 = startKey1;
        key2 = startKey2;
        half1 = new CaesarCipher(key1);
        half2 = new CaesarCipher(key2);
    }

    public String encryptTwoKeys (String input) {
        System.out.println("==============");
        StringBuilder encrypted = new StringBuilder(input);
        String encrypted1 = half1.encrypt(input);
        String encrypted2 = half2.encrypt(input);
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
    
    public String decryptTwoKeys (String input) {
        System.out.println("==============");
        StringBuilder decrypted = new StringBuilder(input);
        String decrypted1 = half1.decrypt(input);
        String decrypted2 = half2.decrypt(input);
        for (int i = 0; i < decrypted.length(); i++) {
            if ((i + 1) % 2 == 1){
                char currChar = decrypted1.charAt(i);
                decrypted.setCharAt(i, currChar);
            }
            if ((i + 1) % 2 == 0){
                char currChar = decrypted2.charAt(i);
                decrypted.setCharAt(i, currChar);
            }
        }
        System.out.println(decrypted.toString());
        return decrypted.toString();
    }
    
}
