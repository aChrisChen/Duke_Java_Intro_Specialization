import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of coldest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class coldest {
    public CSVRecord coldestOfTwo(CSVRecord coldestSoFar, CSVRecord cur) {
        if ( coldestSoFar == null){
            coldestSoFar = cur;
        } else {
            double soFarTemp= Double.parseDouble(coldestSoFar.get("TemperatureF"));
            double curTemp = Double.parseDouble(cur.get("TemperatureF"));
            if (curTemp < soFarTemp && curTemp != -9999) {
                coldestSoFar = cur;
            }
        }
        return coldestSoFar;
    }
  
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord record : parser) {
            coldestSoFar = coldestOfTwo(coldestSoFar, record);
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile() {
        System.out.println("==============");
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldest.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar =  coldestOfTwo(coldestSoFar, currentRow);
        }
        System.out.println("coldest temperature: "+coldestSoFar.get("TemperatureF"));
        String result = "weather-"+coldestSoFar.get("DateUTC").substring(0,10)+".csv";
        return result;
    }
    
    public double averageTemperatureInFile (CSVParser parser, int minHum) {
        int count = 0;
        double total = 0;
        for (CSVRecord record : parser) {
            if (Integer.parseInt(record.get("Humidity")) >= minHum){
                count = count + 1;
                total = total + Double.parseDouble(record.get("TemperatureF"));
        
            }
        }
        return total/count;
    }
    
    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource();
        int minHum = 80;
        double avg = averageTemperatureInFile(fr.getCSVParser(), minHum);
        String result = "Average temperature in file is "+avg+" with min Huminity "+minHum;
        System.out.println(result);
    }
    
    
    public CSVRecord lowestOfTwo(CSVRecord lowestSoFar, CSVRecord cur) {
        if ( lowestSoFar == null && cur.get("Humidity") != "N/A"){
            lowestSoFar = cur;
        } 
        if (!cur.get("Humidity").equals("N/A")){
            double soFarHum= Double.parseDouble(lowestSoFar.get("Humidity"));
            double curHum = Double.parseDouble(cur.get("Humidity"));
            if (curHum < soFarHum) {
                lowestSoFar = cur;
            }
        }
        return lowestSoFar;
    }
  
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord record : parser) {
            lowestSoFar = lowestOfTwo(lowestSoFar, record);
        }
        return lowestSoFar;
    }
    
    public void testlowestHumidityInFile (){
        FileResource fr = new FileResource();
        CSVRecord lowestSoFar = lowestHumidityInFile(fr.getCSVParser());
        String result = "Lowest Humidity was "+lowestSoFar.get("Humidity")+" at "+lowestSoFar.get("DateUTC");
        System.out.println(result);
    }
    
    public String lowestHumidityInManyFiles () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar =  lowestOfTwo(lowestSoFar, currentRow);
        }
        String result = "Lowest Humidity was "+lowestSoFar.get("Humidity")+" at "+lowestSoFar.get("DateUTC");
        System.out.println(result);
        return result;
    }
}   
