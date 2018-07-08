import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        int currIndex = startIndex;
        while (stopIndex != -1){
            if ((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            }
            stopIndex = dna.indexOf(stopCodon, currIndex);
            currIndex = stopIndex + 1;
        }
        return dna.length();
    }
    
    public StorageResource getAllGenes(String dna){
        System.out.println("*****************");
        dna = dna.toLowerCase();
        int count = 0;
        int currIndex = 0;
        StorageResource store = new StorageResource();
        while (true){
            int startIndex = dna.indexOf("atg", currIndex);
            int tagstopIndex = findStopCodon(dna, startIndex, "tag");
            int taastopIndex = findStopCodon(dna, startIndex, "taa");
            int tgastopIndex = findStopCodon(dna, startIndex, "tga");
            int stopIndex = Math.min(tagstopIndex, Math.min(taastopIndex, tgastopIndex));
       
            currIndex = stopIndex + 3;
            if (startIndex >=0 && stopIndex < dna.length()){
                count = count + 1;
                //System.out.println(startIndex);
                //System.out.println(stopIndex);
                //System.out.println(dna.substring(startIndex, stopIndex + 3));            
                String sub = dna.substring(startIndex, stopIndex + 3);
                store.add(sub);
            }
            if (startIndex < 0 || currIndex >= dna.length()){
                break;
            }   
        }
        //System.out.println(count);
        return store;
    }
    
    
    public double cgRatio(String dna){
        //System.out.println("*******************");
        int a = 0;
        int c = 0;
        int g = 0;
        int t = 0;
        dna = dna.toLowerCase();
        for (char x : dna.toCharArray()){
            if (x == 'a'){
                a = a + 1;
            }
            if (x == 'c'){
                c = c + 1;
            }
            if (x == 'g'){
                g = g + 1;
            }
            if (x == 't'){
                t = t + 1;
            }
        }
        int total = a + c + g + t;
        int cg = c + g;
        double cg_p = cg/total;
        String result = a+" "+c+" "+g+" "+t;
        //System.out.println(result);
        return (double)cg/total;
    }       
    
    public void processGenes (StorageResource sr){
        int tenChar = 0;
        int cgHigh = 0;
        int longestLength = 0;
        String longest = "";
        for (String item : sr.data()){
            if (item.length() > 60){
                tenChar = tenChar + 1;
                //System.out.println("Long string: "+item);
            }
            if (cgRatio(item) > 0.35){
                cgHigh = cgHigh + 1;
                //System.out.println("High cg Ration: "+item);            
            }
            if (item.length() > longestLength){
                longest = item;
                longestLength = item.length();
            }
        }
        
        System.out.println("Gene #: "+sr.size());
        System.out.println("tenChar: "+tenChar);
        System.out.println("cgHigh: "+cgHigh);
        System.out.println("The longest "+longest);
        System.out.println("with length "+longestLength);
    }
    
    public int countPiece(String dna, String piece){
        dna = dna.toLowerCase();
        piece = piece.toLowerCase();
        int count = 0;
        int startIndex = dna.indexOf(piece);
        while (startIndex != -1){
            count = count + 1;
            startIndex = dna.indexOf(piece, startIndex + 1);
        }
        return count;
    }
    
    public void testProcessGenes () {
        System.out.println("===============");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        //String dna = "cccatgaatagataataggagaadfasdfatgtagfadfatgtaafadfatgtgasd";
        
        dna = dna.toLowerCase();
        StorageResource s = getAllGenes(dna);
        
        processGenes(s);
        System.out.println("CTG #:"+countPiece(dna,"CTG"));
    }
    
    
}
