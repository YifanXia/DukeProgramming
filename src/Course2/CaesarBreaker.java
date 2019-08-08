package Course2;

import edu.duke.FileResource;

public class CaesarBreaker {

    public int[] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[alphabet.length()];
        for (int i = 0; i < message.length(); i ++) {
            int index = alphabet.indexOf(Character.toLowerCase(message.charAt(i)));
            if (index != -1) {
                counts[index] ++;
            }
        }
        return counts;
    }

    public int indexOfMax(int[] values) {
        int indMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[indMax]) {
                indMax = i;
            }
        }
        return indMax;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] letterCounts = countLetters(encrypted);
        int maxIndex = indexOfMax(letterCounts);
        int dKey = maxIndex - 4;
        if (maxIndex < 4) {
            dKey = 26 + dKey;
        }
        return cc.encrypt(encrypted, 26 - dKey);
    }

    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        String encrypted = cc.encrypt(message, 13);
        System.out.println(decrypt(encrypted));
        encrypted = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        System.out.println(decrypt(encrypted));
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
    }
}
