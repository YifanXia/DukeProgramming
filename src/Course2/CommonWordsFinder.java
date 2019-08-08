package Course2;

import edu.duke.FileResource;

public class CommonWordsFinder {

    public String[] getCommon() {

        FileResource fs = new FileResource("resources/data/common.txt");
        String[] commonWords = new String[20];
        int index = 0;
        for (String word : fs.words()) {
            commonWords[index] = word;
            index ++;
        }

        return commonWords;

    }

    public int indexOf(String[] list, String word) {
        for (int i = 0; i < list.length; i ++) {
            if (list[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    public void countWords(FileResource resource, String[] common, int[] counts){
        for (String word: resource.words()) {
            int index = indexOf(common, word.toLowerCase());
            if (index != -1) {
                counts[index] += 1;
            }
        }
    }

    void countShakespeare(){
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        //String[] plays = {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int k=0; k < plays.length; k++){
            FileResource resource = new FileResource("resources/data/" + plays[k]);
            countWords(resource,common,counts);
            System.out.println("done with " + plays[k]);
        }

        for(int k=0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }

    public static void main(String[] args) {
        CommonWordsFinder commonWordsFinder = new CommonWordsFinder();
        commonWordsFinder.countShakespeare();
    }
}
