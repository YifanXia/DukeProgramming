public class AllGeneFinder {

    /**
     * Find the first index of a specified stop codon. otherwise return 0
     * @param dnaString
     * @param startIndex
     * @param stopCodon
     * @return
     */
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
        return dnaString.length();
    }
}
