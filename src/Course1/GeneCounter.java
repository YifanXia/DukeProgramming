package Course1;

public class GeneCounter {

    private AllGeneFinder allGeneFinder = new AllGeneFinder();
    private MultipleOccurencesFinder multipleOccurencesFinder = new MultipleOccurencesFinder();

    public int countGenes(String dna) {
        int count = 0;
        int startPoint = 0;
        while (startPoint >= 0) {
            String gene = allGeneFinder.findGene(dna, startPoint);
            if (gene.isEmpty()) {
                break;
            }
            else {
                startPoint = dna.indexOf(gene, startPoint) + gene.length();
                count = count + multipleOccurencesFinder.howMany(gene, dna);
            }
        }
        return count;
    }

    public void testCountGenes() {
        String dna = "CATGCCGGATGTAATTTAGTTTTCATGCGGATGTAATTTAGTTGACATGCGATGTAATTTAGTCTGACATGCCGGATGTAATTTAGTTTTCATGCGGATGTAATTTAGTTGACATGCGATGTAATTTAGTCTGA";
        int count = countGenes(dna);
        if (count != 6) throw new AssertionError("Wrong count");
    }

    public static void main(String[] args) {
        GeneCounter geneCounter = new GeneCounter();
        geneCounter.testCountGenes();
    }

}
