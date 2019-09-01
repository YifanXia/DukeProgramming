package Course3.InterfaceAndAbstractClass;
/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }

    public void runMarkov() {
        FileResource fr = new FileResource("resources/random_text/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 40;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
        //String st = "yes-this-is-a-thin-pretty-\n" +
        //        "pink-thistle";
        FileResource fr = new FileResource("resources/random_text/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel mm = new MarkovModel(5);
        mm.setTraining(st.trim());
        mm.setRandom(531);
        mm.buildMap();
        mm.printHashMapInfo();
    }

	public static void main(String[] args) {
        MarkovRunnerWithInterface mri = new MarkovRunnerWithInterface();
        //mri.runMarkov();
        mri.testHashMapBuilder();
    }
}
