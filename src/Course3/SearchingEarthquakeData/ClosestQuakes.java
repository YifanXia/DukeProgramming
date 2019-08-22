package Course3.SearchingEarthquakeData;
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeCopy = new ArrayList<>(quakeData);
        for (int i = 0; i < howMany; i ++) {
            double minDistance = Double.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < quakeCopy.size(); j ++) {
                double currentDist = quakeCopy.get(j).getLocation().distanceTo(current);
                if (currentDist < minDistance) {
                    minDistance = currentDist;
                    minIndex = j;
                }
            }
            ret.add(quakeCopy.get(minIndex));
            quakeCopy.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "resources/earthquakeData/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }

    public static void main(String[] args) {
        ClosestQuakes cq = new ClosestQuakes();
        cq.findClosestQuakes();
    }
    
}
