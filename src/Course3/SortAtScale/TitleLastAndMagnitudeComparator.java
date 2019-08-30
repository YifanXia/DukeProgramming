package Course3.SortAtScale;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public TitleLastAndMagnitudeComparator() {}

    public String getLastWord(String info) {
        String[] words = info.split(" ");
        return words[words.length - 1];
    }

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastWord1 = getLastWord(q1.getInfo());
        String lastWord2 = getLastWord(q2.getInfo());
        int result = lastWord1.compareTo(lastWord2);
        if (result != 0) {
            return result;
        }
        else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}
