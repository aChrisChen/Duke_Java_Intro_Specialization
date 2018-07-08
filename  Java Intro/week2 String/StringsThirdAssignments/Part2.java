import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna){
        System.out.println("*******************");
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
        System.out.println(result);
        return (double)cg/total;
    }   
}
