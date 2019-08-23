package Course3.FilteringData;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {

    private ArrayList<Filter> filters;
    public MatchAllFilter() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f: filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        String filterNames = "Filters used are: ";
        for (Filter f: filters) {
            filterNames += f.getName() + ", ";
        }
        return filterNames;
    }

}
