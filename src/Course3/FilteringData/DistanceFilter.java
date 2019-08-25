package Course3.FilteringData;

public class DistanceFilter implements Filter {

    private Location location;
    private double maxDistance;
    private String filterName;

    public DistanceFilter(Location location, double maxDistance) {
        this.location = location;
        this.maxDistance = maxDistance;
        this.filterName = "DistanceFilterDefault";
    }

    public DistanceFilter(Location location, double maxDistance, String filterName) {
        this.location = location;
        this.maxDistance = maxDistance;
        this.filterName = filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(location) < maxDistance);
    }

    @Override
    public String getName() {
        return filterName;
    }

}
