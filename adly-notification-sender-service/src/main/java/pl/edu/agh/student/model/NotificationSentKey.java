package pl.edu.agh.student.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@PrimaryKeyClass
public class NotificationSentKey implements Serializable {

    @PrimaryKeyColumn(name = "device_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID deviceId;

    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Date date;

    public NotificationSentKey(UUID deviceId, Date date) {
        this.deviceId = deviceId;
        this.date = date;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotificationSentKey{" +
                "deviceId=" + deviceId +
                ", date=" + date +
                '}';
    }
}
