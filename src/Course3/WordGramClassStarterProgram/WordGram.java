package Course3.WordGramClassStarterProgram;

import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = hashCode();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (String word: myWords) {
            ret += word;
            ret += " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (myWords.length != other.length()) {
            return false;
        }
        else {
            for (int i = 0; i < myWords.length; i ++) {
                if (!myWords[i].equals(other.wordAt(i))) {
                    return false;
                }
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        String[] outWords = new String[myWords.length];
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for (int i = 0; i < myWords.length - 1; i ++) {
            outWords[i] = myWords[i + 1];
        }
        outWords[myWords.length - 1] = word;
        return new WordGram(outWords, 0, outWords.length);
    }

}