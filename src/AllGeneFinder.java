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
            else {
                minIndex = taaIndex;
            }
            if (minIndex == -1) {
                return "";
            }
            return dna.substring(startIndex, minIndex + 3);
        }
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
}
