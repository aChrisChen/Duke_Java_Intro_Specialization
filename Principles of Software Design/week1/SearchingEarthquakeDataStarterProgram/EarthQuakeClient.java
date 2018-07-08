import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    private String dataSource = "data/nov20quakedata.atom";
    
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry le : quakeData) {
            if (le.getMagnitude() > magMin) {
                answer.add(le);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry le : quakeData) {
            Location loc = le.getLocation();
            //.distanceTo return with meter
            if (from.distanceTo(loc) < distMax*1000) {
                answer.add(le);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        System.out.println("======================");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = dataSource;
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> big = filterByMagnitude(list, 5.0);
        for (QuakeEntry le: big) {
            System.out.println(le);
        }
    }

    public void closeToMe(){
        System.out.println("======================");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = dataSource;
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city1 = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> close1 = filterByDistanceFrom(list, 1000, city1);
        System.out.println("***This location is Durham, NC***");
        for (QuakeEntry le: close1) {
            System.out.println(le);
        }
        System.out.println("found "+close1.size()+" quakes that match that criteria");
        
        // This location is Bridgeport, CA
        Location city2 = new Location ("Bridgeport, CA");
        ArrayList<QuakeEntry> close2 = filterByDistanceFrom(list, 1000, city2);
        System.out.println("***This location is Bridgeport, CA***");
        for (QuakeEntry le: close2) {
            System.out.println(le);
        }
        System.out.println("found "+close2.size()+" quakes that match that criteria");
        
        // Location city =  new Location(38.17, -118.82);
        Location city3 = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close3 = filterByDistanceFrom(list, 1000, city3);
        System.out.println("***new Location(38.17, -118.82)***");
        for (QuakeEntry le: close3) {
            System.out.println(le);
        }
        System.out.println("found "+close3.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    //filter by depth
    public ArrayList<QuakeEntry> filterByDepth (ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        System.out.println("======================");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = dataSource;
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> quakes = filterByDepth(list, -4000, -2000);
        System.out.println("Find quakes with depth between -4000 and -2000");
        for (QuakeEntry le: quakes) {
            System.out.println(le);
        }
        System.out.println("found "+quakes.size()+" quakes that match that criteria");
    }    
    
    //filter by phrase
    public ArrayList<QuakeEntry> filterByPhrase (ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            switch(where) {
                case "start":
                    if (info.startsWith(phrase)) {
                        answer.add(qe);
                    }
                    break;
                case "any":
                    if (info.indexOf(phrase) != -1) {
                        answer.add(qe);
                    }
                    break;
                case "end":
                    if (info.endsWith(phrase)) {
                        answer.add(qe);
                    }
                    break;
                default:
                    break;
            }
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        System.out.println("======================");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = dataSource;
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> quakes = filterByPhrase(list, "any", "Creek");
        System.out.println("Find quakes with phrase");
        for (QuakeEntry le: quakes) {
            System.out.println(le);
        }
        System.out.println("found "+quakes.size()+" quakes that match phrase");
    }    
}
