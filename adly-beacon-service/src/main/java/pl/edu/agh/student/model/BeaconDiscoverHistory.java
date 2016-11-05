package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("beacon_discover_history")
public class BeaconDiscoverHistory {

    @PrimaryKey
    private UuidAndDateKey uuidAndDateKey;

    @Column("date")
    private Date date;

    @Column("trace_id")
    private UUID traceId;

    public BeaconDiscoverHistory() {
    }

    public BeaconDiscoverHistory(UuidAndDateKey uuidAndDateKey, Date date, UUID traceId) {
        this.uuidAndDateKey = uuidAndDateKey;
        this.date = date;
        this.traceId = traceId;
    }

    public UuidAndDateKey getUuidAndDateKey() {
        return uuidAndDateKey;
    }

    public void setUuidAndDateKey(UuidAndDateKey uuidAndDateKey) {
        this.uuidAndDateKey = uuidAndDateKey;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "BeaconDiscoverHistory{" +
                "uuidAndDateKey=" + uuidAndDateKey +
                ", date=" + date +
                ", traceId=" + traceId +
                '}';
    }
}
