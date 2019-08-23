package Course3.SearchingEarthquakeData;

import java.util.*;

public class LargestQuakes {

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int maxIndex = 0;
        for (int i = 0; i < quakeData.size(); i ++) {
            if (quakeData.get(maxIndex).getMagnitude() < quakeData.get(i).getMagnitude()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeCopy = new ArrayList<>(quakeData);
        if (quakeCopy.size() < howMany) {
            howMany = quakeCopy.size();
        }
        for (int i = 0; i < howMany; i ++) {
            int maxIndex = indexOfLargest(quakeCopy);
            ret.add(quakeCopy.get(maxIndex));
            quakeCopy.remove(maxIndex);
        }
        return ret;
    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "resources/earthquakeData/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());


        ArrayList<QuakeEntry> large = getLargest(list, 5);
        for (QuakeEntry qe: large) {
            System.out.println(qe);
        }
        System.out.println("number found: "+large.size());
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }

}
