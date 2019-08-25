package Course1;

import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.io.File;

public class GeneStorer {

    private AllGeneFinder allGeneFinder = new AllGeneFinder();
    private MultipleOccurencesFinder mof = new MultipleOccurencesFinder();

    //private StorageResource geneList = new StorageResource();

    public StorageResource getAllGenes(String dna) {
        int startPoint = 0;
        StorageResource geneList = new StorageResource();
        while (startPoint >= 0) {
            String gene = allGeneFinder.findGene(dna, startPoint);
            if (gene.isEmpty()) {
                break;
            }
            else {
                startPoint = dna.indexOf(gene, startPoint) + gene.length();
                geneList.add(gene);
            }
        }
        return geneList;
    }

    public double cgRatio(String dna) {
        int dnaLength = dna.length();
        int countCs = mof.howMany("C", dna);
        int countGs = mof.howMany("G", dna);
        return (double) (countCs + countGs) / dnaLength;
    }

    public int countCTG(String dna) {
        return mof.howMany("CTG", dna);
    }

    public void processGenes(StorageResource sr) {
        int countLongGenes = 0;
        int countHighCGRatio = 0;
        int largestLength = 0;
        for (String gene: sr.data()) {
            if (gene.length() > 60) {
                countLongGenes ++;
                System.out.println("The gene " + gene + " is long.");
            }
            if (cgRatio(gene) > 0.35) {
                countHighCGRatio ++;
                System.out.println("The gene " + gene + " has high C-G Ratio of " + cgRatio(gene) + ".");
            }
            if (gene.length() > largestLength) {
                largestLength = gene.length();
            }
        }
        System.out.println("Number of long genes: " + countLongGenes);
        System.out.println("Number of high C-G ratio genes:" + countHighCGRatio);
        System.out.println("Longest gene: " + largestLength);
    }

    public void testGetAllGenes() {
        String dna = "CATGCCGGATGTAATTTAGTTTTCATGCGGATGTAATTTAGTTGACATGCGATGTAATTTAGTCTGA";
        StorageResource geneList = getAllGenes(dna);
        for (String gene: geneList.data()) {
            System.out.println(gene);
        }
    }

    public void testProcessGenes() {
        String dna = "CATGCCGGATGTAATTTAGTTTTCATGCGGATGTAATTTAGTTGACATGCGATGTAATTTAGTCTGA";
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource("resources/dna/GRch38dnapart.fa");
        GeneStorer geneStorer = new GeneStorer();
        String dna = fr.asString().toUpperCase();

        StorageResource geneList = geneStorer.getAllGenes(dna);
        System.out.println("Number of genes: " + geneList.size());
        geneStorer.processGenes(geneList);
        System.out.println("Number of CTGs: " + geneStorer.countCTG(dna));
        //geneStorer.testGetAllGenes();
        //geneStorer.testProcessGenes();

    }
}
