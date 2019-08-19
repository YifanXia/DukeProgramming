package Course2.Vigenere;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sliced.append(message.charAt(i));
        }
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i ++) {
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere () {
        String encrypted = new FileResource("src/Course2/Vigenere/messages/secretmessage1.txt").asString();
        int[] key = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        String message = "Football does not mean the same thing in US and UK.";
        System.out.println(vb.sliceString(message, 1, 5));
        String encrypted1 = new FileResource("src/Course2/Vigenere/messages/secretmessage1.txt").asString();
        int[] key = vb.tryKeyLength(encrypted1, 4, 'e');
        for (int k: key) {
            System.out.print(k + " ");
        }
        System.out.println();
        vb.breakVigenere();
    }
    
}
