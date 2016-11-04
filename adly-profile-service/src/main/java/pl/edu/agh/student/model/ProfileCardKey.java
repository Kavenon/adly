package pl.edu.agh.student.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@PrimaryKeyClass
public class ProfileCardKey implements Serializable {

    @PrimaryKeyColumn(name = "profile_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID profileId;

    @PrimaryKeyColumn(name = "property_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Integer propertyId;

    public ProfileCardKey() {
    }

    public ProfileCardKey(UUID profileId, Integer propertyId) {
        this.profileId = profileId;
        this.propertyId = propertyId;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public String toString() {
        return "ProfilePropertyKey{" +
                "profileId=" + profileId +
                ", propertyId=" + propertyId +
                '}';
    }
}
