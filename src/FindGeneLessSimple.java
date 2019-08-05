
/* Some basic useful methods
    public String s = "dukeprogramming";
    String x = s.substring(4, 7); // 4 is included, 7 is excluded, the substring is of 3 letters
    int len = x.length();
    int subStart = s.indexOf("program");
    int missingIndex = s.indexOf("f"); // returns -1
    int gIndexFirst = s.indexOf("g");
    int gIndexSecond = s.indexOf("g", 8);
 */
public class FindGeneLessSimple {

    public String findGeneSimple(String dna, String startCodon, String stopCodon) {
        // start codon is ATG
        // stop codon is TAA
        String result = "";
        if (checkUpperCase(dna)) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int indexStartCodon = dna.indexOf(startCodon);
        if (indexStartCodon == -1) { // No ATG
            return "";
        }
        int indexStopCodon = dna.indexOf(stopCodon, indexStartCodon + 3);
        if (indexStopCodon == -1) { // No TAA
            return "";
        }
        if ((indexStopCodon - indexStartCodon) % 3 != 0) {
            return "";
        }
        result = dna.substring(indexStartCodon, indexStopCodon + 3);
        return result;
    }

    /**
     * Check if the DNA string is uppercase, assuming that all characters in the string are upper/lower case
     * @param dna
     * @return True if the string is uppercase
     */
    public boolean checkUpperCase(String dna) {
        char[] charArray = dna.toCharArray();
        return Character.isUpperCase(charArray[0]);
    }

    public void testFindGeneSimple() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String dna = "AATGCCGTAATATGGT";
        System.out.println("DNA is " + dna);
        String gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "CGTAATATGGT";
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "AATGCG";
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "AATGCGTAATATGGT".toLowerCase();
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "AATGGCGTAATATGGT".toLowerCase();
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

    }

    public static void main(String[] args) {
        FindGeneLessSimple findGeneLessSimple = new FindGeneLessSimple();
        findGeneLessSimple.testFindGeneSimple();
    }
}
