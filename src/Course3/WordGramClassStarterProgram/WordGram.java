package Course3.WordGramClassStarterProgram;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = hashCode();
    }

    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < myWords.length; i ++) {
            hash += myWords[i].hashCode() * (-1) ^ i;
        }
        return hash;
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
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        return out;
    }

}