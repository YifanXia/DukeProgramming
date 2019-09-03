package Course3.WordNGramStarterProgram;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private HashMap<String, ArrayList<String>> myMap;
    
    public MarkovWordOne() {
        myRandom = new Random();
        myMap = new HashMap<>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] text, String word, int start) {
    	for (int i = start; i < text.length; i ++) {
    		if (text[i].equals(word)) {
    			return i;
			}
		}
    	return -1;
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
    	if (myMap.containsKey(key)) {
    		return myMap.get(key);
		}
    	else {
			ArrayList<String> follows = new ArrayList<String>();
    		int keyIdx = indexOf(myText, key, 0);
    		while (keyIdx + 1 < myText.length && keyIdx != -1) {
    			follows.add(myText[keyIdx + 1]);
    			int pos = keyIdx + 1;
    			keyIdx = indexOf(myText, key, pos);
			}
    		myMap.put(key, follows);
    		return follows;
		}
    }

}
