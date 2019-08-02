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
        int numPoint = 0;
        for (Point pt: s.getPoints()) {
            numPoint ++;
        }
        return numPoint;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numEdges = getNumPoints(s);
        double avgLength = perimeter / numEdges;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestDist = 0.0;
        for (Point currPt: s.getPoints()) {
            double dist = currPt.distance(prevPt);
            if (dist >= largestDist) {
                largestDist = dist;
            }
            prevPt = currPt;
        }
        return largestDist;
    }

    public double getLargestX(Shape s) {
        double largestX = - Double.MAX_VALUE;
        for (Point pt: s.getPoints()) {
            if (pt.getX() > largestX) {
                largestX = pt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0.0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file: directoryResource.selectedFiles()) {
            FileResource fr = new FileResource(file);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > largestPerim) {
                largestPerim = getPerimeter(s);
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        File fileLargestPerim = null;
        double largestPerim = 0.0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file: directoryResource.selectedFiles()) {
            FileResource fr = new FileResource(file);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > largestPerim) {
                largestPerim = getPerimeter(s);
                fileLargestPerim = file;
            }
        }
        assert fileLargestPerim != null;
        return fileLargestPerim.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("average length = " + avgLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {

        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
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
        pr.testPerimeter();
    }
}
