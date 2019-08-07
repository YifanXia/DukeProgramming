package Course2;

public class CaesarCipher {

    public String reverseFor(String s) {
        String ret = "";
        int k; // define the variable k outside the loop to be referred afterwards
        for (k=0; k<s.length(); k ++) {
            ret = s.charAt(k) + ret;
        }
        return ret;
    }

    public String reverseWhile(String s) {
        String ret = "";
        int k = 0;
        while (k < s.length()) {
            ret = s.charAt(k) + ret;
            k ++;
        }
        return ret;
    }

    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encrypted = new StringBuilder(input);
        int i;
        for (i = 0; i < input.length(); i ++) {
            char letter = input.charAt(i);
            int letterIndex = alphabet.indexOf(Character.toUpperCase(letter));
            if (letterIndex != -1) {
                char newLetter = shiftedAlphabet.charAt(letterIndex);
                if (Character.isLowerCase(letter)) {
                    newLetter = Character.toLowerCase(newLetter);
                }
                encrypted.setCharAt(i, newLetter);
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt() {
        String initial = "I am BATMAN";
        int key = 3;
        String encrypted = encrypt(initial, key);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        caesarCipher.testEncrypt();
    }

}
