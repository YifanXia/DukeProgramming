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

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String word: fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }

    public int[] countWords(String message, HashSet<String> dictionary) {
        String[] splitMessage = message.split("\\W+");
        int wordCount = splitMessage.length;
        int validCount = 0;
        for (String word: splitMessage) {
            if (dictionary.contains(word.toLowerCase())) {
                validCount ++;
            }
        }
        int[] counts = {validCount, wordCount};
        return counts;
    }

    public char mostCommonLetterIn(HashSet<String> dict) {
        char mostCommonLetter = '*';
        int mostCommonLetterCount = 0;
        HashMap<Character, Integer> characterCounts = new HashMap<>();
        for (String word: dict) {
            for (char letter: word.toLowerCase().toCharArray()) {
                if (characterCounts.keySet().contains(letter)) {
                    int letterCount = characterCounts.get(letter);
                    characterCounts.put(letter, letterCount + 1);
                }
                else {
                    characterCounts.put(letter, 1);
                }
            }
        }
        for (char letter: characterCounts.keySet()) {
            if (mostCommonLetterCount < characterCounts.get(letter)) {
                mostCommonLetter = letter;
                mostCommonLetterCount = characterCounts.get(letter);
            }
        }
        return mostCommonLetter;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int mostValidCounts = 0;
        String mostValidCountsLang = "";
        String decrypted = "";
        for (String lang: languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            char mostCommonLetter = mostCommonLetterIn(dictionary);
            String decryptedLang = breakForLanguage(encrypted, dictionary, mostCommonLetter);
            int validCount = countWords(decryptedLang, dictionary)[0];
            System.out.println(lang + ": " + validCount);
            if (mostValidCounts < validCount) {
                mostValidCounts = validCount;
                mostValidCountsLang = lang;
                decrypted = decryptedLang;
            }
        }
        System.out.println(mostValidCounts);
        System.out.println(mostValidCountsLang);
        System.out.println(decrypted);
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonLetter) {
        int maxValidCount = 0;
        String decrypted = "";
        int[] validKey = null;
        for (int i = 1; i <= 100; i ++) {
            int[] key = tryKeyLength(encrypted, i, mostCommonLetter);
            VigenereCipher vc = new VigenereCipher(key);
            String decryptedTemp = vc.decrypt(encrypted);
            int[] counts = countWords(decryptedTemp, dictionary);
            if (maxValidCount < counts[0]) {
                maxValidCount = counts[0];
                decrypted = decryptedTemp;
                validKey = key;
            }
        }
        return decrypted;
    }

    public void breakVigenere () {
        String encrypted = new FileResource("src/Course2/Vigenere/test_data/aida_keyverdi.txt").asString();
        String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> dicts = new HashMap<>();
        for (String lang: langs) {
            FileResource langDictResource = new FileResource("resources/dictionaries/" + lang);
            dicts.put(lang, readDictionary(langDictResource));
        }
        breakForAllLangs(encrypted, dicts);
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource engDictResource = new FileResource("resources/dictionaries/English");
        HashSet<String> engDict = vb.readDictionary(engDictResource);
        String message = "Football does not mean the same thing in US and UK.";
        System.out.println(vb.sliceString(message, 1, 5));
        String encrypted1 = new FileResource("src/Course2/Vigenere/messages/secretmessage2.txt").asString();
        int[] key = vb.tryKeyLength(encrypted1, 38, 'e');
        for (int k: key) {
            System.out.print(k + " ");
        }
        System.out.println();
        String decrypted38 = new VigenereCipher(key).decrypt(encrypted1);
        int[] counts = vb.countWords(decrypted38, engDict);
        System.out.println("valid counts with key 38: " + counts[0]);
        vb.breakVigenere();
    }
    
}
