
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;

     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String ip = le.getIpAddress();
             if (!uniqueIPs.contains(ip)){
                 uniqueIPs.add(ip);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum (int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }   
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
         ArrayList<String> uniqueIPSomeday = new ArrayList<String>();
         for (LogEntry le : records){
             String day = le.getAccessTime().toString().substring(4,10);
             //System.out.println(le.getAccessTime().toString());
             if (day.equals(someday)) {
                if (!uniqueIPSomeday.contains(le.getIpAddress())){
                    uniqueIPSomeday.add(le.getIpAddress());
                }
             }
         }
         return uniqueIPSomeday;
     }
     
     public int countUniqueIPsInRange (int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             if (le.getStatusCode() >= low && le.getStatusCode() <= high){
                if(!uniqueIPs.contains(le.getIpAddress())){
                    uniqueIPs.add(le.getIpAddress());
                }
             }
         }
         return uniqueIPs.size();
     }
     
     //======================================================
     //using HashMap below
     public HashMap<String, Integer> countVisitsPerIP () {
         HashMap<String, Integer> counts = new HashMap<String, Integer> ();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip)){
                 counts.put(ip, 1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP (HashMap<String, Integer> counts) {
         int max = 0;
         for (String ip : counts.keySet()) {
             if (counts.get(ip) > max){
                 max = counts.get(ip);
             }
         }
         return max;
     }
     
     public ArrayList<String> IPsMostVisits(HashMap<String, Integer> counts) {
         int max = mostNumberVisitsByIP(counts);
         ArrayList<String> IPs = new ArrayList<String>();
         for (String ip : counts.keySet()){
             if (counts.get(ip) == max){
                 IPs.add(ip);
             }
         }
         return IPs;
     }
     
     public HashMap<String, ArrayList<String>> IPsForDays(){
        HashMap<String, ArrayList<String>> days = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records) {
            String day = le.getAccessTime().toString().substring(4,10);
            if (!days.containsKey(day)){
                ArrayList<String> IPs = new ArrayList<String> ();
                IPs.add(le.getIpAddress());
                days.put(day, IPs);
            } else {
                ArrayList<String> IPs = days.get(day);
                IPs.add(le.getIpAddress());
                days.put(day, IPs);              
            }
        }
        return days;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days) {
         int max = 0;
         String maxDay = new String();
         for (String day : days.keySet()){
             if (days.get(day).size() > max){
                maxDay = day;
                max = days.get(day).size();
             }
         }
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> days, String day){
         ArrayList<String> visits = days.get(day);
         HashMap<String, Integer> counts = new HashMap<String, Integer> ();
         for (String ip : visits) {
             if (!counts.containsKey(ip)){
                 counts.put(ip, 1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return IPsMostVisits(counts);
     }
}
