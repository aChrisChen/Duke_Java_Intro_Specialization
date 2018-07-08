
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String a, String b){
        int first = b.indexOf(a);
        if (first == -1){
            return true;
        }
        int second = b.indexOf(a, first + a.length());
        if (second == -1){
            return true;
        }
        return false;
    }
    
    public String lastPart(String a, String b){
        int first = b.indexOf(a);
        if (first == -1){
            return b;
        } else{
            return b.substring(first + a.length(), b.length());
        }
    }
    
    public void testing() {
        String a = "by";
        String b = "A story by Abby Long";
        boolean presult = false;
        
        boolean result = twoOccurrences(a, b);
        System.out.println("==============");
        if (presult == result ){
            System.out.println("Success checking for '"+a+"' in '"+b+"'");
        } else {
            System.out.println("Fail");
        }
    }
}
