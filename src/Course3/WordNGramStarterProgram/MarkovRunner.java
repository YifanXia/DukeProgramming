package Course3.WordNGramStarterProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import Course3.WordGramClassStarterProgram.EfficientMarkovWord;
import Course3.WordGramClassStarterProgram.MarkovWord;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource("resources/random_text/confucius.txt");
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne();
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 120, 832);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    public void testHashMapBuilder() {
        String st = "this is a test yes this is really a test";
        FileResource fr = new FileResource("resources/random_text/confucius.txt");
        //String st = fr.asString();
        //st = st.replace('\n', ' ');
        EfficientMarkovWord mm = new EfficientMarkovWord(2);
        mm.setTraining(st.trim());
        mm.setRandom(42);
        mm.printHashMapInfo();
    }

    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
        mr.runMarkov();
        //mr.testHashMapBuilder();
    }

}
