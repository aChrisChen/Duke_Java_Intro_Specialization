
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter () {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies (QuakeEntry qe) {
        for (Filter currF : filters) {
            if (!currF.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName (){
        System.out.println("**Include these filters:**");
        for (Filter currF: filters) {
            System.out.println(currF.getName());
        }
        return "finish_printing";
    }
}
