
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testGetFollows (String key) {
        System.out.println("=======================");
        String s = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(s);
        ArrayList<String> result = markov.getFollows(key);
        System.out.println(result);
    }
    
    public void testGetFollowsWithFile (String key) {
        System.out.println("=======================");
        FileResource fr = new FileResource();

        MarkovOne markov = new MarkovOne();
        markov.setTraining(fr.asString().replace('\n', ' '));
        ArrayList<String> result = markov.getFollows(key);
        System.out.println(result.size());
    }
}
