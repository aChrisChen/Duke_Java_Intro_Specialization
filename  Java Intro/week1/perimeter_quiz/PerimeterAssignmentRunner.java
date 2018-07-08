import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for (Point Pt : s.getPoints()){
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double length = getPerimeter(s);
        int count = getNumPoints(s);
        return (double)(length/count);
    }

    public double getLargestSide(Shape s) {
        double largest = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if (currDist > largest){
                largest = currDist;
            }
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        double largest = 0;
        for (Point pt : s.getPoints()){
            double x = pt.getX();
            if (x > largest){
                largest = x;
            }
        }
        return largest;
    }

    public double getLargestPerimeterMultipleFiles(DirectoryResource dr) {
        double largest = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largest) {
                largest = length;
            }
        }
        return largest;
    }

    public String getFileWithLargestPerimeter(DirectoryResource dr) {
        File temp = null;
        double largest = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largest) {
                largest = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        double length = getPerimeter(s);
        int count = getNumPoints(s);
        double avg = getAverageLength(s);
        double lside = getLargestSide(s);
        double lx = getLargestX(s);
        
        System.out.println("=========== Single ============");
        System.out.println("perimeter = " + length);
        System.out.println("NumPoints = " + count);
        System.out.println("AverageLength = " + avg);
        System.out.println("LargestSide = " + lside);
        System.out.println("Largest X = " + lx);
    }
    
    public void testPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double ll = getLargestPerimeterMultipleFiles(dr);
        System.out.println("=========== Multi ============");
        System.out.println("Largest Length = " + ll);
    }

    public void testFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        String lf = getFileWithLargestPerimeter(dr);
        System.out.println("=========== Multi ============");
        System.out.println("File with Largest Length = " + lf);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
