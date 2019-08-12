package Course1;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyBirthName {

    String path = "resources/csv/baby_names/us_babynames_test/";

    public void totalBirths(FileResource fr) {
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        CSVParser csv = fr.getCSVParser(false);
        for (CSVRecord rec: csv) {
            String name = rec.get(0);
            String gender = rec.get(1);
            String counts = rec.get(2);
            System.out.println("Name: " + name + ", Gender: " + gender + ", Number: " + counts);
            if (gender.equals("F")) {
                totalGirls += Integer.parseInt(counts);
                totalGirlNames ++;
            }
            else {
                totalBoys += Integer.parseInt(counts);
                totalBoyNames ++;
            }
        }
        int totalBirths = totalBoys + totalGirls;
        int totalNames = totalBoyNames + totalGirlNames;
        System.out.println("Boy names: " + totalBoyNames + ", Boys born: " + totalBoys);
        System.out.println("Girl names: " + totalGirlNames + ", Girls born: " + totalGirls);
        System.out.println("Total names: " + totalNames + ", Total born: " + totalBirths);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource("resources/csv/baby_names/us_babynames_test/yob2014short.csv");
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender) {
        String fileName = path + "yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        for (CSVRecord currentRec: fr.getCSVParser(false)) {
            if (currentRec.get(1).equalsIgnoreCase(gender)) {
                rank ++;
                if (currentRec.get(0).equalsIgnoreCase(name)) {
                    return rank;
                }
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        BabyBirthName babyBirthName = new BabyBirthName();
        babyBirthName.testTotalBirths();
    }
}
