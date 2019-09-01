package Course3.InterfaceAndAbstractClass;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected HashMap<String, ArrayList<String>> followsOfKeys;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
        followsOfKeys = new HashMap<>();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
        followsOfKeys.clear();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);

    ArrayList<String> getFollows(String key) {
        if (followsOfKeys.containsKey(key)) {
            return followsOfKeys.get(key);
        }
        else {
            ArrayList<String> follows = new ArrayList<>();
            int pos = 0;
            int keyIndex = myText.indexOf(key, pos);
            while (keyIndex + key.length() < myText.length() && keyIndex != -1) {
                String follow = Character.toString(myText.charAt(keyIndex + key.length()));
                follows.add(follow);
                pos = keyIndex + 1;
                keyIndex = myText.indexOf(key, pos);
            }
            followsOfKeys.put(key, follows);
            return follows;
        }
    }

}
