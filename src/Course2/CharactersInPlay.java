package Course2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> characterCounts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }

    private void update(String name) {
        int indexOfName = characters.indexOf(name);
        if (indexOfName == -1) {
            characters.add(name);
            characterCounts.add(1);
        }
        else {
            int previousCount = characterCounts.get(indexOfName);
            characterCounts.set(indexOfName, previousCount + 1);
        }
    }

    public void findAllCharacters(FileResource fr) {
        characters.clear();
        characterCounts.clear();
        for (String line: fr.lines()) {
            int indexOfCharacterName = line.indexOf(".");
            if (indexOfCharacterName != -1) {
                String name = line.substring(0, indexOfCharacterName);
                update(name);
            }
        }
    }

    public void charactersWithNumParts(int lower, int upper) {
        for (int i = 0; i < characters.size(); i ++) {
            int count = characterCounts.get(i);
            if (lower <= count && count < upper) {
                System.out.println(characters.get(i) + " " + count);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource("resources/data/macbethSmall.txt");
        findAllCharacters(fr);
        for (int i = 0; i < characters.size(); i ++) {
            System.out.println(characters.get(i) + " " + characterCounts.get(i));
        }
        charactersWithNumParts(1, 3);
    }

    public static void main(String[] args) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester();
    }
}
