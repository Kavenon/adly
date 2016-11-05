package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("beacon_discover_history")
public class BeaconDiscoverHistory {

    @PrimaryKey
    private UuidAndDateKey uuidAndDateKey;

    @Column("beacon_id")
    private Integer beaconId;

    @Column("trace_id")
    private UUID traceId;

    public BeaconDiscoverHistory() {
    }

    public BeaconDiscoverHistory(UuidAndDateKey uuidAndDateKey, Integer beaconId, UUID traceId) {
        this.uuidAndDateKey = uuidAndDateKey;
        this.beaconId = beaconId;
        this.traceId = traceId;
    }

    public UuidAndDateKey getUuidAndDateKey() {
        return uuidAndDateKey;
    }

    public void setUuidAndDateKey(UuidAndDateKey uuidAndDateKey) {
        this.uuidAndDateKey = uuidAndDateKey;
    }

    public Integer getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Integer beaconId) {
        this.beaconId = beaconId;
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
                "traceId=" + traceId +
                ", beaconId=" + beaconId +
                ", uuidAndDateKey=" + uuidAndDateKey +
                '}';
    }
}
