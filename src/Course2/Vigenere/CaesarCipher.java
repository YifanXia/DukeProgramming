package Course2.Vigenere;

import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }
    
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }
    
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }
    
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    public String toString() {
        return "" + theKey;
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher(4);
        char oc = 'a';
        char ec = cc.encryptLetter(oc);
        System.out.println(oc);
        System.out.println(ec);
        String om = new FileResource("src/Course2/Vigenere/test_data/titus-small.txt").asString();
        String em = cc.encrypt(om);
        System.out.println(om);
        System.out.println(em);
    }
    
}
