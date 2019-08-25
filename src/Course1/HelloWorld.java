package Course1;

import edu.duke.*;

public class HelloWorld {

    public static void runHello() {
        FileResource res = new FileResource("resources/hello_unicode.txt");
        for (String line : res.lines()) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.runHello();
    }

}
