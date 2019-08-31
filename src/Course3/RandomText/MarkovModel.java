package Course3.RandomText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MarkovModel {

    private String myText;
    private int keyLength;
    private Random myRandom;
    private HashMap<String, ArrayList<String>> followsOfKeys;

    public MarkovModel(int keyLength) {
        this.myRandom = new Random();
        this.keyLength = keyLength;
        this.followsOfKeys = new HashMap<>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

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

    public ArrayList<String> getFollows(String key) {
        if (followsOfKeys.containsKey(key)) {
            return followsOfKeys.get(key);
        }
        else {
            int pos = 0;
            int keyIndex = 0;
            ArrayList<String> follows = new ArrayList<>();
            while (true) {
                keyIndex = myText.indexOf(key, pos);
                if (keyIndex + key.length() < myText.length() && keyIndex != -1) {
                    //System.out.println(keyIndex + " " + myText.length());
                    String follow = Character.toString(myText.charAt(keyIndex + key.length()));
                    follows.add(follow);
                    pos = keyIndex + 1;
                }
                else {
                    break;
                }
            }
            followsOfKeys.put(key, follows);
            return follows;
        }
    }
}
