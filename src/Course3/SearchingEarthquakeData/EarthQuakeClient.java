package Course3.SearchingEarthquakeData;

import java.util.*;
import edu.duke.*;

import javax.script.ScriptEngine;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> filteredData = new ArrayList<>();
        for (QuakeEntry qe: quakeData) {
            double depth = qe.getDepth();
            if (minDepth < depth && depth < maxDepth) {
                filteredData.add(qe);
            }
        }
        return filteredData;
    }
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
	
	public void bigQuakes() {
	    EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "resources/earthquakeData/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
	}
	
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "resources/earthquakeData/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "resources/earthquakeData/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("Found " + close.size() + " close earthquakes.");

    }

    public void quakeOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "resources/earthquakeData/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> filtered = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Earthquakes between the depth of " + minDepth + " and " + maxDepth + ":");
        for (int k = 0; k < filtered.size(); k ++) {
            QuakeEntry qe = filtered.get(k);
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() + " such quakes.");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> filtered = new ArrayList<>();
        for (QuakeEntry qe: quakeData) {
            switch (where) {
                case "start":
                    if (qe.getInfo().startsWith(phrase)) {
                        filtered.add(qe);
                    }
                    break;
                case "end":
                    if (qe.getInfo().endsWith(phrase)) {
                        filtered.add(qe);
                    }
                    break;
                case "any":
                    if (qe.getInfo().indexOf(phrase) != -1) {
                        filtered.add(qe);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + where);
            }
        }
        return filtered;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "resources/earthquakeData/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        ArrayList<QuakeEntry> filtered = filterByPhrase(list, "any", "Can");
        for (QuakeEntry qe: filtered) {
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() + " entries.");
    }

    public static void main(String[] args) {
        EarthQuakeClient eqc = new EarthQuakeClient();
        //eqc.createCSV();
        //eqc.bigQuakes();
        //eqc.closeToMe();
        eqc.quakeOfDepth();
        eqc.quakesByPhrase();
    }
}
