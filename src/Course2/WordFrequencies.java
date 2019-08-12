package Course2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for (String word: fr.words()) {
            int index = myWords.indexOf(word.toLowerCase());
            if (index == -1) {
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public int findIndexMax() {
        int maxInd = 0;
        int maxFreq = myFreqs.get(maxInd);
        for (int i = 1; i < myWords.size(); i ++) {
            if (maxFreq < myFreqs.get(i)) {
                maxInd = i;
                maxFreq = myFreqs.get(maxInd);
            }
        }
        return maxInd;
    }

    public void tester() {
        findUnique();
        System.out.println("# of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i ++) {
            System.out.println("Word: " + myWords.get(i) + ", its count is: " + myFreqs.get(i));
        }
        int indexMax = findIndexMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(indexMax) + " " + myFreqs.get(indexMax));
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.tester();
    }
}
