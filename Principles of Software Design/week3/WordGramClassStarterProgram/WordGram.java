import java.util.*;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int i=0; i<length(); i++) {
            ret += myWords[i];
            ret += " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        
        if (length() != other.length()) {
            return false;
        }
        for (int i=0; i < length(); i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }         
        return true;
    }

    public WordGram shiftAdd(String word) { 
        String[] shifted = new String[length()+1];
        
        System.arraycopy(myWords, 0, shifted, 0, length());
        shifted[length()] = word;
        WordGram out = new WordGram(shifted, 1, myWords.length);
        
        return out;
    }
    
    public int hashCode() {
        String gramContents = this.toString();
        int hashNum = gramContents.hashCode();
        return hashNum;
    }

}