package Course1;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.File;

public class WeatherAnalyser {

    private CSVRecord getLargerOfTwo(CSVRecord currentRecord, CSVRecord largestSoFar, String key) {
        double currentKey = Double.parseDouble(currentRecord.get(key));
        double largestKeySoFar = Double.parseDouble(largestSoFar.get(key));
        if (currentKey > largestKeySoFar) {
            largestSoFar = currentRecord;
        }
        return largestSoFar;
    }

    private CSVRecord getLowerOfTwo(CSVRecord currentRecord, CSVRecord coldestSoFar, String key) {
        double currentKey = Double.parseDouble(currentRecord.get(key));
        double lowestKeySoFar = Double.parseDouble(coldestSoFar.get(key));
        if (currentKey < lowestKeySoFar && currentKey != -9999.) {
            coldestSoFar = currentRecord;
        }
        return coldestSoFar;
    }

    private boolean isNotNA(CSVRecord record, String key) {
        return !record.get(key).equals("N/A");
    }

    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRecord: parser) {
            if (largestSoFar == null) {
                largestSoFar = currentRecord;
            }
            else {
                largestSoFar = getLargerOfTwo(currentRecord, largestSoFar, "TemperatureF");
            }
        }
        return largestSoFar;
    }

    public CSVRecord hottestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord hottestSoFar = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord hottestInFile = hottestHourInFile(fr.getCSVParser());
            if (hottestSoFar == null) {
                hottestSoFar = hottestInFile;
            }
            else {
                hottestSoFar = getLargerOfTwo(hottestSoFar, hottestInFile, "TemperatureF");
            }
        }
        return hottestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRecord: parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRecord;
            }
            else {
                coldestSoFar = getLowerOfTwo(currentRecord, coldestSoFar, "TemperatureF");
            }
        }
        return coldestSoFar;
    }

    public CSVRecord coldestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f.getAbsolutePath());
            CSVRecord coldestInFile = hottestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = coldestInFile;
            }
            else {
                coldestSoFar = getLowerOfTwo(coldestInFile, coldestSoFar, "TemperatureF");
            }
        }
        return coldestSoFar;
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        String fileName = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f.getAbsolutePath());
            //f.get
            CSVRecord coldestInFile = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = coldestInFile;
                fileName = f.getAbsolutePath();
            }
            else {
                coldestSoFar = getLowerOfTwo(coldestInFile, coldestSoFar, "TemperatureF");
                if (coldestSoFar.get("DateUTC").equals(coldestInFile.get("DateUTC"))) {
                    fileName = f.getAbsolutePath();
                }
            }
        }
        System.out.println(coldestSoFar.get("TemperatureF"));
        return fileName;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRecord: parser) {
            if (isNotNA(currentRecord, "Humidity")) {
                if (lowestSoFar == null) {
                    lowestSoFar = currentRecord;
                }
                else {
                    lowestSoFar = getLowerOfTwo(currentRecord, lowestSoFar, "Humidity");
                }
            }

        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumiditySoFar = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord lowestHInFile = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumiditySoFar == null) {
                lowestHumiditySoFar = lowestHInFile;
            }
            else {
                lowestHumiditySoFar = getLowerOfTwo(lowestHInFile, lowestHumiditySoFar, "Humidity");
            }
        }
        return lowestHumiditySoFar;
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double sumTemperature = 0.0;
        int numHours = 0;
        for (CSVRecord record: parser) {
            double temperature = Double.parseDouble(record.get("TemperatureF"));
            if (temperature != -9999.) {
                sumTemperature += temperature;
                numHours += 1;
            }
        }
        return sumTemperature / numHours;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, double highHumidity) {
        double sumTemperature = 0.0;
        int numHours = 0;
        for (CSVRecord record: parser) {
            double temperature = Double.parseDouble(record.get("TemperatureF"));
            double humidity = Double.parseDouble(record.get("Humidity"));
            if (temperature != -9999. && humidity >= highHumidity) {
                sumTemperature += temperature;
                numHours += 1;
            }
        }
        return sumTemperature / numHours;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.println("Lowest temperature is " + coldestHour.get("TemperatureF") + " at " + coldestHour.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        CSVRecord coldestHourInFile = coldestHourInFile(new FileResource(fileName).getCSVParser());
        System.out.println(fileName);
        System.out.println(coldestHourInFile.get("TemperatureF"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity is " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testLowestHumidityInManyDays() {
        CSVRecord lowestHumidity = lowestHumidityInManyDays();
        System.out.println("Lowest humidity is " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature is: " + averageTemperatureInFile(parser));
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(avg)) {
            System.out.println("Nothing found");
        }
        else {
            System.out.println("Average temperature with high humidity is: " + avg);
        }

    }

    public static void main(String[] args) {
        WeatherAnalyser weatherAnalyser = new WeatherAnalyser();
        //weatherAnalyser.testColdestHourInFile();
        weatherAnalyser.testFileWithColdestTemperature();
        //weatherAnalyser.testLowestHumidityInFile();
        //weatherAnalyser.testLowestHumidityInManyDays();
        //weatherAnalyser.testAverageTemperatureInFile();
        //weatherAnalyser.testAverageTemperatureWithHighHumidityInFile();
        //weatherAnalyser.testAverageTemperatureWithHighHumidityInFile();
    }


}
