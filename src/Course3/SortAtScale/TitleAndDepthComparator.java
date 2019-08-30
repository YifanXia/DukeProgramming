package Course3.SortAtScale;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public TitleAndDepthComparator() {}

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int result = q1.getInfo().compareTo(q2.getInfo());
        if (result != 0) {
            return result;
        }
        else {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
    }
}
