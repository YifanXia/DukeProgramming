package Course3.WordGramClassStarterProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private int myOrder;
    private Random myRandom;
    private HashMap<Integer, ArrayList<String>> myMap;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<>();
    }

    public EfficientMarkovWord(int order, int seed) {
        myOrder = order;
        myRandom = new Random(seed);
        myMap = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void printHashMapInfo() {
        /*
        Print the HashMap (all the keys and their corresponding values). Only do this if the HashMap is small.
        Print the number of keys in the HashMap
        Print the size of the largest value in the HashMap—that is, the size of the largest ArrayList of characters
        Print the keys that have the maximum size value.
         */
        //System.out.println(myMap);
        System.out.println("Number of keys: " + myMap.keySet().size());
        int maxListLength = 0;
        for (int key: myMap.keySet()) {
            if (maxListLength < myMap.get(key).size()) {
                maxListLength = myMap.get(key).size();
            }
        }
        ArrayList<Integer> maxKeys = new ArrayList<>();
        for (int key: myMap.keySet()) {
            if (myMap.get(key).size() == maxListLength) {
                maxKeys.add(key);
            }
        }
        System.out.println("Max length is: " + maxListLength);
        System.out.println("Max key is: " + maxKeys);
    }

    private int indexOf(String[] text, WordGram wordGram, int start) {
        for (int i = start; i < text.length - wordGram.length(); i ++) {
            String[] textArr = new String[wordGram.length()];
            System.arraycopy(text, i, textArr, 0, wordGram.length());
            String textStr = "";
            for (String word: textArr) {
                textStr += word;
                textStr += " ";
            }
            if (textStr.trim().equals(wordGram.toString())) {
                return i;
            }
        }
        return -1;
    }

    private void buildMap() {
        for (int i = 0; i <= myText.length - myOrder; i ++) {
            System.out.println(i / (myText.length * 1.0));
            ArrayList<String> follows = new ArrayList<>();
            WordGram key = new WordGram(myText, i, myOrder);
            //System.out.println(key);
            int pos = 0;
            int keyIdx = indexOf(myText, key, pos);
            while (keyIdx + key.length() < myText.length && keyIdx != -1) {
                follows.add(myText[keyIdx + key.length()]);
                pos = keyIdx + 1;
                keyIdx = indexOf(myText, key, pos);
                //System.out.println(keyIdx);
            }
            myMap.put(key.hashCode(), follows);
        }
    }

    private ArrayList<String> getFollows(WordGram key) {
        return myMap.getOrDefault(key.hashCode(), null);
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }
}
