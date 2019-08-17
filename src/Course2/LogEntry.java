package Course2;

import java.util.Date;
import edu.duke.*;

public class LogEntry {

    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int byteReturned;

    public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int byteReturned) {
        this.ipAddress = ipAddress;
        this.accessTime = accessTime;
        this.request = request;
        this.statusCode = statusCode;
        this.byteReturned = byteReturned;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getByteReturned() {
        return byteReturned;
    }

    public String toString() {
        return ipAddress + " " + accessTime + " " + request + " " + statusCode + " " + byteReturned;
    }

}
