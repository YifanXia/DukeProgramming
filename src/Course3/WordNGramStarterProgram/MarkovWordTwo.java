package Course3.WordNGramStarterProgram;

import java.lang.reflect.Array;
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private HashMap<String[], ArrayList<String>> myMap;

    public MarkovWordTwo() {
        myRandom = new Random();
        myMap = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public int indexOf(String[] text, String[] words, int start) {
        for (int i = start; i < text.length - words.length; i ++) {
            if (text[i].equals(words[0]) && text[i + 1].equals(words[1])) {
                return i;
            }
        }
        return -1;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String[] key = Arrays.copyOfRange(myText, index, index + 2);
        sb.append(String.join(" ", key));
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(follows.size());
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = new String[] {key[key.length - 1], next};
        }
        System.out.println(myMap.keySet().size());
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String[] key) {
        if (myMap.containsKey(key)) {
            return myMap.get(key);
        }
        else {
            ArrayList<String> follows = new ArrayList<String>();
            int pos = 0;
            int keyIdx = indexOf(myText, key, pos);
            while (keyIdx + key.length < myText.length && keyIdx != -1) {
                follows.add(myText[keyIdx + key.length]);
                pos = keyIdx + 1;
                keyIdx = indexOf(myText, key, pos);
            }
            myMap.put(key, follows);
            return follows;
        }
    }
}
