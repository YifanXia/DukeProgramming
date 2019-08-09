package Course1;

import edu.duke.FileResource;
import org.apache.commons.csv.*;

public class ExportDataParser {

    public CSVParser tester() {
        FileResource fr = new FileResource();
        return fr.getCSVParser();
    }

    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record: parser) {
            if (record.get("Country").toLowerCase().equals(country.toLowerCase())) {
                String output = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return output;
            }
        }
        return "";
    }
}
