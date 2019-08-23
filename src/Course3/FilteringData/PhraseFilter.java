package Course3.FilteringData;

public class PhraseFilter implements Filter {

    private String where;
    private String phrase;
    private String filterName;

    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
        this.filterName = "PhraseFilterDefault";
    }

    public PhraseFilter(String where, String phrase, String filterName) {
        this.where = where;
        this.phrase = phrase;
        this.filterName = filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        switch (where) {
            case "start":
                return qe.getInfo().startsWith(phrase);
            case "end":
                return qe.getInfo().endsWith(phrase);
            case "any":
                return qe.getInfo().contains(phrase);
            default:
                throw new IllegalStateException("Unexpected value: " + where);
        }
    }

    @Override
    public String getName() {
        return filterName;
    }

}
