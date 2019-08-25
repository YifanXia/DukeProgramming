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

    public String emphasize(String phrase, char ch) {
        StringBuilder sbPhrase = new StringBuilder(phrase);
        int i;
        for (i = 0; i < phrase.length(); i ++) {
            // check if vowel
            if (Character.toLowerCase(sbPhrase.charAt(i)) == ch) {
                // odd location (even index)
                if (i % 2 == 0) {
                    sbPhrase.setCharAt(i, '*');
                }
                else { // even location (odd index)
                    sbPhrase.setCharAt(i, '+');
                }
            }

        }
        return sbPhrase.toString();
    }

    public void testIsVowel() {
        char letter = 'F';
        System.out.println(isVowel(letter));
        letter = 'a';
        System.out.println(isVowel(letter));
    }

    public void testReplaceVowels() {
        String phrase = "Hello World";
        System.out.println(replaceVowels(phrase, '*'));
    }

    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        System.out.println(emphasize(phrase, 'a'));
        phrase = "Mary Bella Abracadabra";
        System.out.println(emphasize(phrase, 'a'));
    }

    public static void main(String[] args) {
        WordPlay wordPlay = new WordPlay();
        wordPlay.testIsVowel();
        wordPlay.testReplaceVowels();
        wordPlay.testEmphasize();
    }
}
