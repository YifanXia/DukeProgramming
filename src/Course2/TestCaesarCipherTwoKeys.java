package Course2;

import edu.duke.FileResource;

public class TestCaesarCipherTwoKeys {

    private int[] countLetters(String message) {
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

    private int indexOfMax(int[] values) {
        int indMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[indMax]) {
                indMax = i;
            }
        }
        return indMax;
    }

    private String halfOfString(String message, int start) {
        StringBuilder halfMessage = new StringBuilder("");
        for (int i = start; i < message.length(); i += 2) {
            halfMessage.append(message.charAt(i));
        }
        return halfMessage.toString();
    }

    private int getKey(String message) {
        int[] counts = countLetters(message);
        int maxIndex = indexOfMax(counts);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 + key;
        }
        return key;
    }

    public String breakCaesarCipher(String encrypted) {
        String evenIndexHalf = halfOfString(encrypted, 0);
        String oddIndexHalf = halfOfString(encrypted, 1);
        int key1 = getKey(evenIndexHalf);
        int key2 = getKey(oddIndexHalf);

        CaesarCipherTwoKeysOO cc = new CaesarCipherTwoKeysOO(key1, key2);
        return cc.decryptTwoKeys(encrypted);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String text = fr.asString();
        CaesarCipherTwoKeysOO cc = new CaesarCipherTwoKeysOO(17, 3);
        String encrypted = cc.encryptTwoKeys(text);
        System.out.println(breakCaesarCipher(encrypted));
    }

    public static void main(String[] args) {
        TestCaesarCipherTwoKeys testCaesarCipherTwoKeys = new TestCaesarCipherTwoKeys();
        testCaesarCipherTwoKeys.simpleTests();
    }
}
