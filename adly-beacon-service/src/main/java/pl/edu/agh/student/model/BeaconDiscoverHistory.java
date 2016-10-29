package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("beacon_discover_history")
public class BeaconDiscoverHistory {

    @PrimaryKey
    private UuidAndDateKey uuidAndDateKey;

    @Column("beacon_id")
    private Integer beaconId;

    public BeaconDiscoverHistory() {
    }

    public BeaconDiscoverHistory(UuidAndDateKey uuidAndDateKey, Integer beaconId) {
        this.uuidAndDateKey = uuidAndDateKey;
        this.beaconId = beaconId;
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

    @Override
    public String toString() {
        return "BeaconDiscoverHistory{" +
                "uuidAndDateKey=" + uuidAndDateKey +
                ", beaconId=" + beaconId +
                '}';
    }
}
