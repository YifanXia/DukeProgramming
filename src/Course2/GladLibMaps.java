package Course2;

import edu.duke.*;

import java.io.*;
import java.util.*;

public class GladLibMaps {

    private ArrayList<String> usedWords;
    private HashMap<String, ArrayList<String>> wordMap;
    private Random myRandom;
    private Properties properties;
    private FileInputStream in;
    private HashSet<String> usedLabels;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "resources/data";

    public GladLibMaps(){
        wordMap = new HashMap<String, ArrayList<String>>();
        usedLabels = new HashSet<>();
        myRandom = new Random();
        properties = new Properties();

        try {
            in = new FileInputStream("resources/word_lists.properties");
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not found.");
        }
        try {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("Something is wrong.");
        }
        initializeFromSource(dataSourceDirectory);

    }

    public GladLibMaps(String source){
        wordMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        //String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};

        for (String label: properties.stringPropertyNames()) {
            String fileName = properties.getProperty(label);
            //String fileName = label + ".txt";
            ArrayList<String> wordList = readIt(source + "/" + fileName);
            wordMap.put(label, wordList);
        }
        usedWords = new ArrayList<String>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (!label.equals("number")) {
            usedLabels.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else {
            ArrayList<String> wordList = wordMap.getOrDefault(label, null);
            if (wordList == null) {
                return "**UNKNOWN**";
            }
            else {
                return randomFrom(wordList);
            }
        }
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int indexOfSub = usedWords.indexOf(sub);
        while (indexOfSub != -1) {
            sub = getSubstitute(w.substring(first+1, last));
            indexOfSub = usedWords.indexOf(sub);
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("resources/datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("");
        System.out.println(usedWords.size());
    }

    public int totalWordsInMap() {
        int totalWords = 0;
        for (String label: wordMap.keySet()) {
            totalWords += wordMap.get(label).size();
        }
        return totalWords;
    }

    public int totalWordsConsidered() {
        int totalWords = 0;
        Iterator<String> it = usedLabels.iterator();
        while (it.hasNext()) {
            String label = it.next();
            totalWords += wordMap.get(label).size();
        }
        return totalWords;
    }

    public static void main(String[] args) {
        GladLibMaps gladLibMaps = new GladLibMaps();
        gladLibMaps.makeStory();
        System.out.println(gladLibMaps.totalWordsInMap());
        System.out.println(gladLibMaps.totalWordsConsidered());
    }



}
