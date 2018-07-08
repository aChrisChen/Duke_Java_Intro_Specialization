
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany (String a, String b){
        int startIndex = 0;
        int currIndex = 0;
        int count = 0;
        while(startIndex != -1){
            startIndex = b.indexOf(a, currIndex);
            if (startIndex != -1){
                count = count + 1;
                currIndex = startIndex + a.length();
            }
        }
        return count;
    }
    
    public void testHowMany () {
    System.out.println("=============");
    String a1 = "GAA";
    String b1 = "ATGAACGAATTGAATC";

    String a2 = "AA";
    String b2 = "ATAAAA";
    
    System.out.println(howMany(a1, b1));
    System.out.println(howMany(a2, b2));
    }
}
