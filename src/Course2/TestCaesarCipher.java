package Course2;

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
}
