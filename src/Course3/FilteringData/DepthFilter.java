package Course3.FilteringData;

public class DepthFilter implements Filter {

    private double minDepth;
    private double maxDepth;
    private String filterName;

    public DepthFilter(double minDepth, double maxDepth) {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.filterName = "DepthFilterDefault";
    }

    public DepthFilter(double minDepth, double maxDepth, String filterName) {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.filterName = filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (minDepth <= qe.getDepth() && qe.getDepth() <= maxDepth);
    }

    @Override
    public String getName() {
        return filterName;
    }
}
