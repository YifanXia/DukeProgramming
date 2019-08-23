package Course3.FilteringData;

public class MagnitudeFilter implements Filter {

    private double minMag;
    private double maxMag;
    private String filterName;

    public MagnitudeFilter(double minMag, double maxMag) {
        this.minMag = minMag;
        this.maxMag = maxMag;
        this.filterName = "MagnitudeFilterDefault";
    }


    public MagnitudeFilter(double minMag, double maxMag, String filterName) {
        this.minMag = minMag;
        this.maxMag = maxMag;
        this.filterName = filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (minMag <= qe.getMagnitude() && qe.getMagnitude() <= maxMag);
    }

    @Override
    public String getName() {
        return filterName;
    }
}
