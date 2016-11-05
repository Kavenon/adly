package pl.edu.agh.student.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
public class UuidAndDateKey implements Serializable {

    @PrimaryKeyColumn(name = "uuid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;

    @PrimaryKeyColumn(name = "beacon_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Integer beaconId;

    public UuidAndDateKey(UUID uuid, Integer beaconId) {
        this.uuid = uuid;
        this.beaconId = beaconId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Integer beaconId) {
        this.beaconId = beaconId;
    }

    @Override
    public String toString() {
        return "UuidAndDateKey{" +
                "uuid=" + uuid +
                ", beaconId=" + beaconId +
                '}';
    }
}

