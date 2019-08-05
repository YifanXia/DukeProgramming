import edu.duke.*;
import java.io.*;

public class Course2Week2QuizSolver {

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = "";
            System.out.println("found " + result);
        }
    }

}
