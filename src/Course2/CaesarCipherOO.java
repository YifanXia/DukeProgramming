package Course2;

public class CaesarCipherOO {

    private String alphabet;
    private String shiftedAlphabet;
    private int key;

    public CaesarCipherOO(int key) {
        this.key = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String encrypt(String input) {
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

    public String decrypt(String encrypted) {
        CaesarCipherOO ccDecrypt = new CaesarCipherOO(26 - key);
        return ccDecrypt.encrypt(encrypted);
    }


    public static void main(String[] args) {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherOO caesarCipherOO = new CaesarCipherOO(15);
        System.out.println(caesarCipherOO.encrypt(message));
    }

}
