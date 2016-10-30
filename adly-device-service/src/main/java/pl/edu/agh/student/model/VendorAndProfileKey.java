package pl.edu.agh.student.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
public class VendorAndProfileKey implements Serializable {

    @PrimaryKeyColumn(name = "vendor_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer vendorId;

    @PrimaryKeyColumn(name = "profile_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID uuid;

    public VendorAndProfileKey(Integer vendorId, UUID uuid) {
        this.vendorId = vendorId;
        this.uuid = uuid;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "VendorAndProfileKey{" +
                "uuid=" + uuid +
                ", vendorId=" + vendorId +
                '}';
    }
}

