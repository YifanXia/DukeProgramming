package Course3.RandomText;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {

    public void testGetFollows() {
        FileResource fr = new FileResource("resources/random_text/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(38);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println(follows.size());
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testGetFollows();
    }
}
