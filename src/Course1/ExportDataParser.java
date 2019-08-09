package Course1;

import edu.duke.FileResource;
import org.apache.commons.csv.*;

public class ExportDataParser {

    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record: parser) {
            if (record.get("Country").toLowerCase().equals(country.toLowerCase())) {
                String output = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return output;
            }
        }
        return "";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            if (record.get("Exports").toLowerCase().contains(exportItem1.toLowerCase())
                    && record.get("Exports").toLowerCase().contains(exportItem2.toLowerCase())) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int counts = 0;
        for (CSVRecord record: parser) {
            if (record.get("Exports").toLowerCase().contains(exportItem.toLowerCase())) {
                counts ++;
            }
        }
        return counts;
    }

    public void bigExporters(CSVParser parser, String amount) {
        int amountLength = amount.length();
        for (CSVRecord record: parser) {
            if (record.get("Value (dollars)").length() > amountLength) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource("resources/csv/exports/exportdata.csv");
        System.out.println(countryInfo(fr.getCSVParser(), "Nauru"));
        listExportersTwoProducts(fr.getCSVParser(), "fish", "nuts");
        System.out.println(numberOfExporters(fr.getCSVParser(), "sugar"));
        bigExporters(fr.getCSVParser(), "$999,999,999,999");
    }

    public static void main(String[] args) {
        ExportDataParser export = new ExportDataParser();
        export.tester();
    }

}
