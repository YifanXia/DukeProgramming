package Course2;

public class WordPlay {

    public boolean isVowel(char ch) {
        String Vowels = "aeoiu";
        return Vowels.indexOf(ch) != -1;
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder noVowels = new StringBuilder(phrase);
        int i;
        for (i = 0; i < phrase.length(); i ++) {
            if (isVowel(phrase.charAt(i))) {
                noVowels.setCharAt(i, ch);
            }
        }
        return noVowels.toString();
    }

    public void testIsVowel() {
        char letter = 'F';
        System.out.println(isVowel(letter));
        letter = 'a';
        System.out.println(isVowel(letter));
    }

    public static void main(String[] args) {
        WordPlay wordPlay = new WordPlay();
        wordPlay.testIsVowel();
    }
}
