package pl.edu.agh.student.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@PrimaryKeyClass
public class UuidAndDateKey implements Serializable {

    @PrimaryKeyColumn(name = "uuid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;

    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Date date;

    public UuidAndDateKey(UUID uuid, Date date) {
        this.uuid = uuid;
        this.date = date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UuidAndDateKey{" +
                "date=" + date +
                ", uuid=" + uuid +
                '}';
    }
}

