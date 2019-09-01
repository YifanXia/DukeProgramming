package Course3.InterfaceAndAbstractClass;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkovModel extends AbstractMarkovModel {

    private int keyLength;

    public MarkovModel(int keyLength) {
        super();
        this.keyLength = keyLength;
    }

    @Override
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - keyLength);
        String key = myText.substring(index, index + keyLength);
        sb.append(key);
        for(int k=0; k < numChars - keyLength; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public void buildMap() {
        followsOfKeys.clear();
        for (int i = 0; i < myText.length() - keyLength + 1; i ++) {
            String key = myText.substring(i, i + keyLength);
            if (!followsOfKeys.containsKey(key)) {
                ArrayList<String> follows = new ArrayList<>();
                int pos = i;
                int keyIndex = myText.indexOf(key, pos);
                while (keyIndex + key.length() < myText.length() && keyIndex != -1) {
                    String follow = Character.toString(myText.charAt(keyIndex + keyLength));
                    follows.add(follow);
                    pos = keyIndex + 1;
                    keyIndex = myText.indexOf(key, pos);
                }
                followsOfKeys.put(key, follows);
            }
        }
    }

    public void printHashMapInfo() {
        /*
        Print the HashMap (all the keys and their corresponding values). Only do this if the HashMap is small.
        Print the number of keys in the HashMap
        Print the size of the largest value in the HashMap—that is, the size of the largest ArrayList of characters
        Print the keys that have the maximum size value.
         */
        //System.out.println(followsOfKeys);
        System.out.println("Number of keys: " + followsOfKeys.keySet().size());
        int maxListLength = 0;
        for (String key: followsOfKeys.keySet()) {
            if (maxListLength < followsOfKeys.get(key).size()) {
                maxListLength = followsOfKeys.get(key).size();
            }
        }
        ArrayList<String> maxKeys = new ArrayList<>();
        for (String key: followsOfKeys.keySet()) {
            if (followsOfKeys.get(key).size() == maxListLength) {
                maxKeys.add(key);
            }
        }
        System.out.println("Max length is: " + maxListLength);
        System.out.println("Max key is: " + maxKeys);
    }

    @Override
    public String toString() {
        return "Markov model of order " + keyLength + ".";
    }
}
