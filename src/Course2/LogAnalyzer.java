package Course2;

import edu.duke.FileResource;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;
    //private HashMap<String, Integer> countsIP;

    public LogAnalyzer() {
        records = new ArrayList<>();
        //countsIP = new HashMap<>();
    }

    public void readFile(String fileName) {
        FileResource fr = new FileResource(fileName);
        for (String line: fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        HashSet<String> uniqueIPs = new HashSet<>();
        for (LogEntry le: records) {
            String ipAddress = le.getIpAddress();
            uniqueIPs.add(ipAddress);
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int sCode) {
        for (LogEntry le: records) {
            if (le.getStatusCode() > sCode) {
                System.out.println(le);
            }
        }
    }

    public HashSet<String> uniqueIPVisitsDay(String someday){
        HashSet<String> uniqueIPs = new HashSet<>();
        for (LogEntry le: records) {
            if (le.getAccessTime().toString().contains(someday)) {
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        HashSet<String> uniqueIPs = new HashSet<>();
        for (LogEntry le: records) {
            if (low <= le.getStatusCode() && le.getStatusCode() <= high) {
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry le: records) {
            if (counts.containsKey(le.getIpAddress())) {
                int newCount = counts.get(le.getIpAddress()) + 1;
                counts.put(le.getIpAddress(), newCount);
            }
            else {
                counts.put(le.getIpAddress(), 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxNumVisits = 0;
        for (String ip: counts.keySet()) {
            if (maxNumVisits < counts.get(ip)) {
                maxNumVisits = counts.get(ip);
            }
        }
        return maxNumVisits;
    }

    public HashSet<String> iPsMostVisits(HashMap<String, Integer> counts) {
        HashSet<String> uniqueIPs = new HashSet<>();
        int maxNumVisits = mostNumberVisitsByIP(counts);
        for (String ip: counts.keySet()) {
            if (counts.get(ip) == maxNumVisits) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dayIPMap = new HashMap<>();
        for (LogEntry le: records) {
            String day = le.getAccessTime().toString().substring(4, 10);
            ArrayList<String> dailyIPs = new ArrayList<>();
            if (dayIPMap.containsKey(day)) {
                dailyIPs = dayIPMap.get(day);
            }
            dailyIPs.add(le.getIpAddress());
            dayIPMap.put(day, dailyIPs);
        }
        return dayIPMap;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIPMap) {
        int maxNumberIPs = 0;
        String maxDay = "";
        for (String day: dayIPMap.keySet()) {
            if (maxNumberIPs < dayIPMap.get(day).size()) {
                maxNumberIPs = dayIPMap.get(day).size();
                maxDay = day;
            }
        }
        return maxDay;
    }

    public HashSet<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIPMap, String day) {
        ArrayList<String> dailyIPs = dayIPMap.get(day);
        HashMap<String, Integer> countIPs = new HashMap<>();
        for (String ip: dailyIPs) {
            if (countIPs.containsKey(ip)) {
                countIPs.put(ip, countIPs.get(ip) + 1);
            }
            else {
                countIPs.put(ip, 1);
            }
        }
        return iPsMostVisits(countIPs);
    }
}
