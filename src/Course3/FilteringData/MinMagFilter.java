package Course3.FilteringData;

/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private String filterName;
    
    public MinMagFilter(double min, String filterName) {
        magMin = min;
        this.filterName = filterName;
    }

    public MinMagFilter(double min) {
        magMin = min;
        this.filterName = "MinMagFilterDefault";
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    }

    public String getName() {
        return filterName;
    }

}
