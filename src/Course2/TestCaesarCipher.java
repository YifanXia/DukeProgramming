package Course2;

import edu.duke.FileResource;

public class TestCaesarCipher {

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

    public void simpleTest() {
        FileResource fr = new FileResource("resources/data/smallHamlet.txt");
        String text = fr.asString();
        CaesarCipherOO cc = new CaesarCipherOO(18);
        String encrypted = cc.encrypt(text);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);

    }

    public static void main(String[] args) {
        TestCaesarCipher test = new TestCaesarCipher();
        test.simpleTest();
    }
}
