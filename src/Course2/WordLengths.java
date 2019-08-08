package Course2;

import edu.duke.FileResource;

public class WordLengths {

    void countWordLengths(FileResource resource, int[] counts) {
        for (String word: resource.words()) {
            int largeLength = counts.length - 1;
            int length = word.length();
            if (!Character.isLetter(word.charAt(0))) {
                length --;
            }
            if (!Character.isLetter(word.charAt(word.length()-1))) {
                length --;
            }
            if (length > largeLength) {
                counts[largeLength] ++;
            }
            else {
                counts[length] ++ ;
            }
        }
    }

    public void testCountWordLengths() {
        int[] lengthCounts = new int[31];
        FileResource resource = new FileResource("resources/data/lotsOfWords.txt");
        countWordLengths(resource, lengthCounts);
        for (int k = 1; k < lengthCounts.length; k ++) {
            if (lengthCounts[k] > 0) {
                System.out.println(lengthCounts[k] + " words of length " + k);
            }
        }
        System.out.println("The most frequent word length is: " + indexOfMax(lengthCounts));
    }

    public int indexOfMax(int[] values) {
        int indMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[indMax]) {
                indMax = i;
            }
        }
        return indMax;
    }

    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}
