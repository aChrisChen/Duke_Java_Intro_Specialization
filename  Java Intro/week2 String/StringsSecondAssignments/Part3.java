
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
    
    public int printAllGenes(String dna){
        System.out.println("==================");
        dna = dna.toLowerCase();
        int count = 0;
        int currIndex = 0;
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
                System.out.println(dna.substring(startIndex, stopIndex + 3));            
            }
            if (startIndex < 0 || currIndex >= dna.length()){
                break;
            }   
        }
        System.out.println(count);
        return count;
    }
    
    void testFindStopCodon(){
        //            012345678901234567890123456789012345678901
        String dna = "cccatgaatagataataggaga";
        String ap =     "atgaatagataatag";
        
        int startIndex = dna.indexOf("atg");
        int stopIndex = findStopCodon(dna, startIndex, "tag");
        System.out.println("==================");
        //System.out.println(startIndex);
        //System.out.println(stopIndex);
        //System.out.println(dna);
        //System.out.println(dna.length());
        String result = dna.substring(startIndex, stopIndex + 3);

        if (ap.equals(result)){
            System.out.println("Success");
        } else {
            System.out.println("dna: "+dna);
            System.out.println("expected: "+ap);
            System.out.println("result: "+result);
        }
        
    }
    
}
