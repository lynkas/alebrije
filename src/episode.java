import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class episode {
    private String stationName;
    private String showName;
    private String episodeName;
    private Date airTime;
    private String description;
    private int dst = 0;
    private SimpleDateFormat dt = new SimpleDateFormat("MM-dd E HH:mm z");
    private String timeStr;

    episode(String stationName, String showName, String episodeName, String description, double time) {
//        dt.setTimeZone(TimeZone.getTimeZone("EST"));
        this.stationName = stationName;
        this.showName = showName;
        this.episodeName = episodeName.replaceAll("; ","*0D0AzX*");
        this.description = description;
        if (TimeZone.getTimeZone("US/Pacific").inDaylightTime(new Date())) dst = 3600;
        airTime = new Date((long) (((time - dst) * 1000)));
//        airTime.setHours(airTime.getHours()+8);
        dt.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        this.timeStr = dt.format(airTime);

    }

    public String getShowName() {
        return showName;
    }


    public String toString() {
        return timeStr + "0D0AzX" + stationName + "0D0AzX*" + showName + "*0D0AzX" + episodeName + "0D0AzX";
    }

    public String toShortString() {
        return timeStr + "0D0AzX" + stationName + "0D0AzX*" + episodeName + "*0D0AzX0D0AzX" + description + "0D0AzX";
    }
}
