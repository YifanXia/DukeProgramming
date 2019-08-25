package Course2;

public class CaesarCipherTwoKeysOO {

    private String alphabet;
    private String shiftedAlphabetA;
    private String shiftedAlphabetB;
    private int key1, key2;

    public CaesarCipherTwoKeysOO(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetA = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabetB = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encryptTwoKeys(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        int i;
        for (i = 0; i < input.length(); i ++) {
            char letter = input.charAt(i);
            int letterIndex = alphabet.indexOf(Character.toUpperCase(letter));
            if (letterIndex != -1) {
                char newLetter;
                if (i % 2 ==0) { // odd location, use key1
                    newLetter = shiftedAlphabetA.charAt(letterIndex);
                }
                else { // even location, use key2
                    newLetter = shiftedAlphabetB.charAt(letterIndex);
                }
                if (Character.isLowerCase(letter)) {
                    newLetter = Character.toLowerCase(newLetter);
                }
                encrypted.setCharAt(i, newLetter);
            }
        }
        return encrypted.toString();
    }

    public String decryptTwoKeys(String encrypted) {
        CaesarCipherTwoKeysOO caesarCipherTwoKeysOO = new CaesarCipherTwoKeysOO(26 - key1, 26 - key2);
        return caesarCipherTwoKeysOO.encryptTwoKeys(encrypted);
    }

    public static void main(String[] args) {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwoKeysOO caesarCipher = new CaesarCipherTwoKeysOO(21, 8);
        System.out.println(caesarCipher.encryptTwoKeys(message));
    }
}
