package Course3.FilteringData;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "resources/earthquakeData/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0);
        MatchAllFilter f = new MatchAllFilter();

        //f.addFilter(new MagnitudeFilter(4.0, 5.0));
        //f.addFilter(new DepthFilter(-35000.0, -12000.0));

        Location tokyo = new Location(35.42, 139.43);
        f.addFilter(new DistanceFilter(tokyo, 10000*1000));
        f.addFilter(new PhraseFilter("end", "Japan"));
        ArrayList<QuakeEntry> m7  = filter(list, f);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "resources/earthquakeData/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "resources/earthquakeData/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0);
        MatchAllFilter f = new MatchAllFilter();

        f.addFilter(new MagnitudeFilter(0.0, 2.0));
        f.addFilter(new DepthFilter(-100000.0, -10000.0));
        f.addFilter(new PhraseFilter("any", "a"));

        //Location tokyo = new Location(35.42, 139.43);
        //f.addFilter(new DistanceFilter(tokyo, 10000*1000));
        //f.addFilter(new PhraseFilter("end", "Japan"));
        ArrayList<QuakeEntry> m7  = filter(list, f);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println(f.getName());
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "resources/earthquakeData/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0);
        MatchAllFilter f = new MatchAllFilter();

        Location tulsa = new Location(36.1314, -95.9372);
        f.addFilter(new MagnitudeFilter(0.0, 3.0));
        f.addFilter(new DistanceFilter(tulsa, 10000*1000));
        f.addFilter(new PhraseFilter("any", "Ca"));
        ArrayList<QuakeEntry> m7  = filter(list, f);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
    }

    public static void main(String[] args) {
        EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
        //eqc2.quakesWithFilter();
        eqc2.testMatchAllFilter();
        eqc2.testMatchAllFilter2();
    }
}
