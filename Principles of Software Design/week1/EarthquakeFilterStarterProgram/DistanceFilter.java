
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    Location currLoc;
    double maxDist;
    
    public DistanceFilter(Location loc, double dist){
        currLoc = loc;
        maxDist = dist;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        double dist = currLoc.distanceTo(qe.getLocation());
        return dist <= maxDist;
    }
    
    public String getName (){
        return "DistanceFilter,loc="+currLoc+",maxDist="+maxDist;
    }
}
