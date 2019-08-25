package Course1;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyBirthName {

    String path_test = "resources/csv/baby_names/us_babynames_test/";
    String path_by_year = "resources/csv/baby_names/us_babynames_by_year/";

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
        FileResource fr = new FileResource("resources/csv/baby_names/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender, boolean useShort) {

        String fileName;

        if (useShort) {
            fileName = path_test + "yob" + year + "short.csv";
        }
        else {
            fileName = path_by_year + "yob" + year + ".csv";
        }

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

    public void testGetRank() {
        System.out.println(getRank(2012, "isabella", "f", true));
        System.out.println(getRank(2012, "mason", "f", true));
        System.out.println(getRank(1960, "emily", "f", false));
        System.out.println(getRank(1971, "frank", "m", false));
    }

    public String getName(int year, int rank, String gender, boolean useShort) {
        String fileName;

        if (useShort) {
            fileName = path_test + "yob" + year + "short.csv";
        }
        else {
            fileName = path_by_year + "yob" + year + ".csv";
        }

        FileResource fr = new FileResource(fileName);

        int currentRank = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            if (record.get(1).equalsIgnoreCase(gender)) {
                currentRank ++;
                if (currentRank == rank) {
                    return record.get(0);
                }
            }
        }
        return "No name";
    }

    public void testGetName() {
        System.out.println(getName(2012, 2, "M", true));
        System.out.println(getName(1980, 350, "f", false));
        System.out.println(getName(1982, 450, "m", false));
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender, boolean useShort) {
        int currentRank = getRank(year, name, gender, useShort);
        String newName = getName(newYear, currentRank, gender, useShort);

        return newName;

    }

    public void testWhatIsNameInYear() {
        String name = "Ava";
        String gender = "f";
        int year = 2012;
        int newYear = 2014;
        String newName = whatIsNameInYear(name, year, newYear, gender, true);
        System.out.println(newName);
        System.out.println(whatIsNameInYear("susan", 1972, 2014, "f", false));
        System.out.println(whatIsNameInYear("owen", 1974, 2014, "m", false));
    }

    public int getRankWithParser(String name, String gender, CSVParser parser) {

        int rank = 0;
        for (CSVRecord currentRec: parser) {
            if (currentRec.get(1).equalsIgnoreCase(gender)) {
                rank ++;
                if (currentRec.get(0).equalsIgnoreCase(name)) {
                    return rank;
                }
            }
        }
        return -1;

    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        int highestRankYear = -1;
        for (File f: dr.selectedFiles()) {
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(fileName.substring(3, 7));
            int rank = getRankWithParser(name, gender, fr.getCSVParser(false));
            if (rank != -1 && rank < highestRank) {
                highestRank = rank;
                highestRankYear = year;
            }
        }
        return highestRankYear;
    }

    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("mich", "m"));
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0;
        int counts = 0;
        for (File f: dr.selectedFiles()) {
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(fileName.substring(3, 7));
            int rank = getRankWithParser(name, gender, fr.getCSVParser(false));
            if (rank != -1) {
                rankSum += rank;
                counts ++;
            }
        }
        if (counts > 0) {
            return rankSum / counts;
        }
        return -1.0;
    }

    public void testGetAverageRank() {
        System.out.println(getAverageRank("robert", "m"));
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender, boolean useShort) {
        String fileName;

        if (useShort) {
            fileName = path_test + "yob" + year + "short.csv";
        }
        else {
            fileName = path_by_year + "yob" + year + ".csv";
        }

        FileResource fr = new FileResource(fileName);

        int totalBirths = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            if (record.get(1).equalsIgnoreCase(gender)) {
                if (record.get(0).equalsIgnoreCase(name)) {
                    return totalBirths;
                }
                totalBirths += Integer.parseInt(record.get(2));
            }
        }
        return totalBirths;
    }

    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "m", false));
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "f", false));
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "m", false));
    }

    public static void main(String[] args) {
        BabyBirthName babyBirthName = new BabyBirthName();
        babyBirthName.testTotalBirths();
        babyBirthName.testGetRank();
        babyBirthName.testGetName();
        babyBirthName.testWhatIsNameInYear();
        //babyBirthName.testYearOfHighestRank();
        //babyBirthName.testGetAverageRank();
        babyBirthName.testGetTotalBirthsRankedHigher();

    }
}
