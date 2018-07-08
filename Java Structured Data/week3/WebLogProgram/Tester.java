
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private String fn = "weblog2_log.txt";
    
    public void testLogEntry() {
        System.out.println("========================");
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        la.printAll();
    }
    
    public void testUniqueIP() {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        System.out.println(la.countUniqueIPs()+" IPs in the records.");
    }
    
    public void testPrintAllHigherThanNum() {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPSomeday() {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
    }

    public void testCountUniqueIPsInRange() {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        System.out.println(la.countUniqueIPsInRange(200,299));
    }
    
    //======================================================
    //using HashMap below
    public void testMostNumberVisitsByIP () {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }
  
    public void testIPsMostVisits () {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.IPsMostVisits(counts));
    }
    
    public void testIPsForDays () {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.IPsForDays());
    }
    
    public void testDayWithMostIPVisits () {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, ArrayList<String>> days = la.IPsForDays();
        for (String day : days.keySet()){
            System.out.println(day+" : "+days.get(day).size());
        }
        System.out.println(la.dayWithMostIPVisits(days));
    }
     
    public void testIPsWithMostVisitsOnDay () {
        System.out.println("========================");
        String filename = "CountingVisitsData/" + fn;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, ArrayList<String>> days = la.IPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(days, "Sep 30"));
    }
}

