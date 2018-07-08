import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


/**
 * Write a description of Baby here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Baby {
    public void totalBirths (FileResource fr) {
        System.out.println("*********************");
        int totalBirths = 0;
	int totalBoys = 0;
	int totalGirls = 0;
	for (CSVRecord rec : fr.getCSVParser(false)) {
	    int numBorn = Integer.parseInt(rec.get(2));
	    totalBirths += numBorn;
	    if (rec.get(1).equals("M")) {
	        totalBoys += numBorn;
	    } else {
		totalGirls += numBorn;
            }
	}
	System.out.println("total births = " + totalBirths);
	System.out.println("female girls = " + totalGirls);
	System.out.println("male boys = " + totalBoys);
    }    

    public void totalNames (FileResource fr) {
        System.out.println("*********************");
        int totalNames = 0;
        int boyNames = 0;
        int girlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalNames += 1;
            if (rec.get(1).equals("M")){
                boyNames += 1;
            }
            if (rec.get(1).equals("F")) {
                girlNames += 1;
            }
        }
        System.out.println("total names: "+ totalNames);
        System.out.println("boy names: "+ boyNames);
        System.out.println("girl names: "+ girlNames);
    }
    public void testTotalNames (int year){
        String filePath = "us_babynames/us_babynames_by_year/"+"yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        totalNames (fr);
    }
    
    public int getRank (int year, String name, String gender) {
        String filePath = "us_babynames/us_babynames_by_year/"+"yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        int exist = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                rank = rank + 1;
            }
            if (record.get(0).equals(name) && record.get(1).equals(gender)){
                exist = 1;
                break;
            }
        }
        //System.out.println(rank);
        if (exist == 1){
            return rank;
        } else {
            return 0;
        }
    }
    
    public String getName (int year, int rank, String gender) {
        String filePath = "us_babynames/us_babynames_by_year/"+"yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        String name = "";
        int currRank = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            String currGender = record.get(1);
            String currName = record.get(0);
            if (currGender.equals(gender)) {
                currRank += 1;
            }
            if (currRank == rank && currGender.equals(gender)) {
                name = currName;
            }

        }
        if (name != ""){
            return name;
        }else {
            return "NO NAME";
        }
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        //String oldFilePath = "us_babynames/us_babynames_test/"+"yob"+year+"short.csv";
        //String newFilePath = "us_babynames/us_babynames_test/"+"yob"+newYear+"short.csv";
        
        String oldFilePath = "us_babynames/us_babynames_by_year/"+"yob"+year+".csv";
        String newFilePath = "us_babynames/us_babynames_by_year/"+"yob"+newYear+".csv";
        FileResource oldFr = new FileResource(oldFilePath);
        FileResource newFr = new FileResource(newFilePath);
        CSVParser oldParser = oldFr.getCSVParser(false);
        CSVParser newParser = newFr.getCSVParser(false);
        
        long rank = getRank(year, name, gender);
        String newName = getName(newYear, (int)rank, gender);
        
        System.out.print(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);

    }
    
    public void yearOfHighestRank (String name, String gender) {
        DirectoryResource dr =  new DirectoryResource();
        int year = -1;
        int highestRank = -1;
        for (File f : dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if (year == -1 && currRank != 0) {
                year = currYear;
                highestRank = currRank;
            }
            if (currRank != 0 && currRank < highestRank){
                year = currYear;
                highestRank = currRank;
            }
        }
        System.out.println(name+" ranks highest at "+highestRank+" in "+year);
    }
    
    public double getAverageRank (String name, String gender) {
        System.out.println("***************");
        DirectoryResource dr =  new DirectoryResource();
        int totalRank = 0;
        int total = 0;
        for (File f : dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if (currRank != 0) {
                totalRank += currRank;
                total += 1;
            }
        }
        //System.out.println("xxxxx");
        //System.out.println(totalRank);
        //System.out.println(total);
        double averageRank = (double)totalRank/total;
        System.out.println(name+" ranks averagely at "+averageRank);
        
        if (total != 0){
            return averageRank;
        } else {
            return -1;
        }
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        System.out.println("******************");
        //String filePath = "us_babynames/us_babynames_test/"+"yob"+year+"short.csv";
        
        String filePath = "us_babynames/us_babynames_by_year/"+"yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        
        int total = 0;
        int currRank = 0;
        int untilRank = getRank(year, name, gender);
        
        for (CSVRecord record : fr.getCSVParser(false)){
            if (!record.get(1).equals(gender)){
                continue;
            }
            currRank += 1;
            if (currRank >= untilRank) {
                break;
            }
            total += Integer.parseInt(record.get(2));
        }
        return total;
    }
    
    
    
    
    
    
    
}
