import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    
    public void testMatchAllFilter () {
        System.out.println("====================");
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        Filter f3 = new PhraseFilter("any", "0");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> result  = filter(list, maf); 
        for (QuakeEntry qe: result) { 
            System.out.println(qe);
        }
    }
    
    public void testMatchAllFilter2 () {
        System.out.println("====================");
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        Location tulsa = new Location(36.1314, -95.9372);
        Location japan = new Location(35.42, 139.43);
        Location denver = new Location(39.7392, -104.9903);
        Location billund = new Location(55.7308, 9.1153);
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        Filter f3 = new DistanceFilter(billund, 3000000);
        Filter f4 = new PhraseFilter("any", "e");
        maf.addFilter(f1);
        //maf.addFilter(f2);
        maf.addFilter(f3);
        maf.addFilter(f4);
        ArrayList<QuakeEntry> result  = filter(list, maf); 
        //for (QuakeEntry qe: result) { 
        //    System.out.println(qe);
        //}
        System.out.println("The length of the reuslt: "+result.size());
    }

    public void quakesWithFilter() { 
        System.out.println("====================");
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        /*
        Filter f1 = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m1  = filter(list, f1); 
        for (QuakeEntry qe: m1) { 
            System.out.println(qe);
        }
        
        
        Filter f2 = new MagnitudeFilter(4.0, 5.0);
        Filter f3 = new DepthFilter (-35000.0, -12000.0);
        ArrayList<QuakeEntry> m2  = filter(list, f2); 
        ArrayList<QuakeEntry> m3  = filter(list, f3); 
        for (QuakeEntry qe: m2) { 
            if(m3.contains(qe)){
                System.out.println(qe);
            }
        }
        */
       Location japan = new Location(35.42, 139.43);
       Filter f4 = new DistanceFilter(japan, 10000000);
       Filter f5 = new PhraseFilter("end", "Japan");
       ArrayList<QuakeEntry> m4  = filter(list, f4); 
       ArrayList<QuakeEntry> m5  = filter(list, f5); 
       for (QuakeEntry qe: m4) { 
           if(m5.contains(qe)){
               System.out.println(qe);
           }
       }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
