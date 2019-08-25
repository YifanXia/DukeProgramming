package Course1;

public class AllGeneFinder {

    /**
     * Find the first index of a specified stop codon. otherwise return 0
     * @param dnaString
     * @param startIndex
     * @param stopCodon
     * @return
     */
    public int findStopCodonOld(String dnaString, int startIndex, String stopCodon) {
        int currIndex = dnaString.indexOf(stopCodon, startIndex);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dnaString.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dnaString.length();
    }

    public int findStopCodon(String dnaString, int startIndex, String stopCodon) {
        int currIndex = dnaString.indexOf(stopCodon, startIndex);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dnaString.indexOf(stopCodon, currIndex + 1);
            }
        }
        return currIndex;
    }

    public void testFindStopCodon() {
        String dna = "CATGCCGGATGTAATTTAGTTT";
        int indTaa = findStopCodon(dna, 1, "TAA");
        int indTag = findStopCodon(dna, 1, "TAG");
        if (indTaa != -1) throw new AssertionError("Wrong TAA index");
        if (indTag != 16) throw new AssertionError("Wrong TAG index");

        dna = "";
        indTaa = findStopCodon(dna, 0, "TAA");
        indTag = findStopCodon(dna, 0, "TAG");
        if (indTaa != -1) throw new AssertionError("Wrong TAA index");
        if (indTag != -1) throw new AssertionError("Wrong TAG index");

    }

    public String findGene(String dna, int startPoint) {
        int startIndex = dna.indexOf("ATG", startPoint);
        if (startIndex == -1) {
            return "";
        }
        else {
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            int minIndex;
            //int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
                minIndex = tagIndex;
            }
            else {
                minIndex = taaIndex;
            }
            if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
                minIndex = tgaIndex;
            }

            if (minIndex == -1) {
                return "";
            }
            return dna.substring(startIndex, minIndex + 3);
        }
    }

    public void testFindGene() {

        String dna = "CATGCCGGATGTAATTTAGTTTT";
        String gene = findGene(dna, 0);
        if (!gene.equals("ATGCCGGATGTAATTTAG")) throw new AssertionError("Wrong gene");

        dna = "CATGCGGATGTAATTTAGTTGA";
        gene = findGene(dna, 0);
        if (!gene.equals("ATGCGGATGTAA")) throw new AssertionError("Wrong gene");

        dna = "CATGCGATGTAATTTAGTCTGA";
        gene = findGene(dna, 0);
        if (!gene.equals("ATGCGATGTAATTTAGTCTGA")) throw new AssertionError("Wrong gene");

        dna = "";
        gene = findGene(dna, 0);
        if (!gene.isEmpty()) throw new AssertionError("Wrong gene");

    }

    public void printAllGenes(String dna) {
        int startPoint = 0;
        while (startPoint >= 0) {
            String gene = findGene(dna, startPoint);
            if (gene.isEmpty()) {
                break;
            }
            else {
                startPoint = dna.indexOf(gene, startPoint) + gene.length();
                System.out.println(gene);
            }
        }
    }

    public void testPrintAllGenes() {
        String dna = "CATGCCGGATGTAATTTAGTTTTCATGCGGATGTAATTTAGTTGACATGCGATGTAATTTAGTCTGA";
        printAllGenes(dna);
    }

    public static void main(String[] args) {
        AllGeneFinder allGeneFinder = new AllGeneFinder();
        allGeneFinder.testFindStopCodon();
        allGeneFinder.testFindGene();
        allGeneFinder.testPrintAllGenes();
        System.out.println(allGeneFinder.findGene("AATGCTAACTAGCTGACTAAT", 0));
    }
}
