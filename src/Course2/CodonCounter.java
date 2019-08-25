package Course2;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCounter {

    private HashMap<String, Integer> codonCounts;

    public CodonCounter() {
        codonCounts = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonCounts.clear();
        dna = dna.trim();
        int startInd = start;
        int endInd = start + 3;
        while (endInd <= dna.length()) {
            String codon = dna.substring(startInd, endInd);
            if (codonCounts.containsKey(codon)) {
                int currentCount = codonCounts.get(codon);
                codonCounts.put(codon, currentCount + 1);
            }
            else {
                codonCounts.put(codon, 1);
            }
            startInd = endInd;
            endInd += 3;
        }
    }

    public String getMostCommonCodon() {
        if (codonCounts.isEmpty()) {
            System.out.println("No codon is counted yet.");
            return "";
        }
        else {
            int currentMax = 0;
            String currentMostCommon = null;
            for (String codon: codonCounts.keySet()) {
                if (currentMax < codonCounts.get(codon)) {
                    currentMostCommon = codon;
                    currentMax = codonCounts.get(codon);
                }
            }
            return currentMostCommon;
        }
    }

    public void printCodonCounts(int start, int end) {
        if (codonCounts.isEmpty()) {
            System.out.println("No codon is counted yet.");
        }
        else {
            for (String codon: codonCounts.keySet()) {
                int currentCodonCount = codonCounts.get(codon);
                if (start <= currentCodonCount && currentCodonCount <= end) {
                    System.out.println(codon + "\t" + currentCodonCount);
                }
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource("resources/dna/dnaMystery2.txt");
        String dna = fr.asString();

        System.out.println("Frame 0");
        buildCodonMap(0, dna);
        System.out.println("Most common codon: " + getMostCommonCodon() + "\t" + codonCounts.get(getMostCommonCodon()));
        printCodonCounts(7, 8);
        System.out.println(codonCounts.size());

        System.out.println("Frame 1");
        buildCodonMap(1, dna);
        System.out.println("Most common codon: " + getMostCommonCodon() + "\t" + codonCounts.get(getMostCommonCodon()));
        System.out.println(codonCounts.size());
        printCodonCounts(6, 7);

        System.out.println("Frame 2");
        buildCodonMap(2, dna);
        System.out.println("Most common codon: " + getMostCommonCodon() + "\t" + codonCounts.get(getMostCommonCodon()));
        printCodonCounts(4, 5);
        System.out.println(codonCounts.size());
    }

    public static void main(String[] args) {
        CodonCounter codonCounter = new CodonCounter();
        codonCounter.tester();
    }
}
