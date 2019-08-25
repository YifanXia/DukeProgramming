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
        /*int[] letterCounts = countLetters(encrypted);
        int maxIndex = indexOfMax(letterCounts);
        int dKey = maxIndex - 4;
        if (maxIndex < 4) {
            dKey = 26 + dKey;
        }*/
        int dKey = getKey(encrypted);
        return cc.encrypt(encrypted, 26 - dKey);
    }

    public String halfOfString(String message, int start) {
        StringBuilder halfMessage = new StringBuilder("");
        for (int i = start; i < message.length(); i += 2) {
            halfMessage.append(message.charAt(i));
        }
        return halfMessage.toString();
    }

    public int getKey(String message) {
        int[] counts = countLetters(message);
        int maxIndex = indexOfMax(counts);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 + key;
        }
        return key;
    }

    public String decryptTwoKeys(String message) {
        StringBuilder fullDecrypted = new StringBuilder("");
        String evenIndexHalf = halfOfString(message, 0);
        String oddIndexHalf = halfOfString(message, 1);
        System.out.println("First key is: " + getKey(evenIndexHalf));
        System.out.println("Second key is: " + getKey(oddIndexHalf));
        String evenDecrypted = decrypt(evenIndexHalf);
        String oddDecrypted = decrypt(oddIndexHalf);
        int shortHalfLength = Math.min(evenIndexHalf.length(), oddIndexHalf.length());
        for (int i = 0; i < shortHalfLength; i ++) {
            fullDecrypted.append(evenDecrypted.charAt(i));
            fullDecrypted.append(oddDecrypted.charAt(i));
        }
        if (evenIndexHalf.length() > oddIndexHalf.length()) {
            int lastIndex = evenIndexHalf.length() - 1;
            fullDecrypted.append(evenDecrypted.charAt(lastIndex));
        }
        return fullDecrypted.toString();
    }

    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        String encrypted = cc.encrypt(message, 13);
        System.out.println(decrypt(encrypted));

    }

    public void testDecryptTwoKeys() {
        //String encrypted = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(decrypted);
    }


    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
        cb.testDecryptTwoKeys();
        FileResource fr = new FileResource("resources/data/mysteryTwoKeysQuiz.txt");
        System.out.println(cb.decryptTwoKeys(fr.asString()));
    }
}
