package Course2;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
        //System.out.println(le.getAccessTime());
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("resources/weblogs/weblog2_log");
        logAnalyzer.printAll();
        System.out.println("Number of unique IPs: " + logAnalyzer.countUniqueIPs());
        logAnalyzer.printAllHigherThanNum(404);
        Iterator<String> it = logAnalyzer.uniqueIPVisitsDay("Sep 27").iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(logAnalyzer.uniqueIPVisitsDay("Sep 27").size());
        System.out.println(logAnalyzer.countUniqueIPsInRange(200, 299));
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(counts);
        System.out.println(logAnalyzer.mostNumberVisitsByIP(counts));
        System.out.println(logAnalyzer.iPsMostVisits(counts));

        HashMap<String, ArrayList<String>> dayIPMap = logAnalyzer.iPsForDays();
        System.out.println(dayIPMap);
        System.out.println(logAnalyzer.dayWithMostIPVisits(dayIPMap));
        System.out.println(logAnalyzer.iPsWithMostVisitsOnDay(dayIPMap, "Sep 29"));
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testLogEntry();
        tester.testLogAnalyzer();
    }
}
