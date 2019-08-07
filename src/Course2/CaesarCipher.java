package Course2;

import java.util.List;

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

    public String decrypt(String encrypted, int key) {
        return encrypt(encrypted, 26 - key);
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        StringBuilder encrypted = new StringBuilder(input);
        int i;
        for (i = 0; i < input.length(); i ++) {
            char letter = input.charAt(i);
            int letterIndex = alphabet.indexOf(Character.toUpperCase(letter));
            if (letterIndex != -1) {
                char newLetter;
                if (i % 2 ==0) { // odd location, use key1
                    newLetter = shiftedAlphabet1.charAt(letterIndex);
                }
                else { // even location, use key2
                    newLetter = shiftedAlphabet2.charAt(letterIndex);
                }
                if (Character.isLowerCase(letter)) {
                    newLetter = Character.toLowerCase(newLetter);
                }
                encrypted.setCharAt(i, newLetter);
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt() {
        String initial = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        String encrypted = encrypt(initial, key);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public void testEncryptTwoKeys() {
        String initial = "First Legion";
        int key1 = 23;
        int key2 = 17;
        String encrypted = encryptTwoKeys(initial, key1, key2);
        System.out.println(encrypted);

        initial = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        key1 = 8;
        key2 = 21;
        encrypted = encryptTwoKeys(initial, key1, key2);
        System.out.println(encrypted);

    }

    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        caesarCipher.testEncrypt();
        caesarCipher.testEncryptTwoKeys();
    }

}
