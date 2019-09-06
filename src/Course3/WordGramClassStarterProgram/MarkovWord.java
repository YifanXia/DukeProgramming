package Course3.WordGramClassStarterProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private int myOrder;
    private Random myRandom;
    private HashMap<Integer, ArrayList<String>> myMap;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public MarkovWord(int order, int seed) {
        myOrder = order;
        myRandom = new Random(seed);
    }

    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
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

    private ArrayList<String> getFollows(WordGram key) {
        if (myMap.containsKey(key.hashCode())) {
            return myMap.get(key.hashCode());
        }
        else {
            ArrayList<String> follows = new ArrayList<String>();
            int pos = 0;
            int keyIdx = indexOf(myText, key, pos);
            while (keyIdx + key.length() < myText.length && keyIdx != -1) {
                follows.add(myText[keyIdx + key.length()]);
                pos = keyIdx + 1;
                keyIdx = indexOf(myText, key, pos);
            }
            myMap.put(key.hashCode(), follows);
            return follows;
        }
    }

    @Override
    public String getRandomText(int numChars) {
        return null;
    }
}
