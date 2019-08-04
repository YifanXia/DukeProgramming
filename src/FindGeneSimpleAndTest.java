public class FindGeneSimpleAndTest {

    public String findGeneSimple(String dna) {
        // start codon is ATG
        // stop codon is TAA
        String result = "";
        int indexStartCodon = dna.indexOf("ATG");
        if (indexStartCodon == -1) { // No ATG
            return "";
        }
        int indexStopCodon = dna.indexOf("TAA", indexStartCodon + 3);
        if (indexStopCodon == -1) { // No TAA
            return "";
        }
        result = dna.substring(indexStartCodon, indexStopCodon + 3);
        return result;
    }

    public void testFindGeneSimple() {
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA is " + dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);

        dna = "CGTAATATGGT";
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);

        dna = "AATGCG";
        System.out.println("DNA is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
    }

    public static void main(String[] args) {
        FindGeneSimpleAndTest findGeneSimpleAndTest = new FindGeneSimpleAndTest();
        findGeneSimpleAndTest.testFindGeneSimple();
    }

}
