import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of export here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class export {
    public String countryInfo (CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (country.equals(record.get("Country"))) {
                String s = record.get("Country")+": "+record.get("Exports")+": "+record.get("Value (dollars)");
                return s;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count = count + 1;
            }
        }
        return count;
    }
    
    public void bigExporters (CSVParser parser, String amount){
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                count = count + 1;
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
            
        }
        System.out.println("Count: "+count);
    }
    
    public void tester() {
        System.out.println("==============");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //String result = countryInfo(parser, "Nauru");
        //System.out.println(result);
        
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        //int count = numberOfExporters(parser, "cocoa");
        //System.out.println(count);
        
        bigExporters(parser, "$999,999,999,999");
    }
}
