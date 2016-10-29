package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table
public class Device {

    @PrimaryKey
    private UUID uuid;

    private UUID profile;

    private String token;
    private Date created = new Date();
    private String system;

    public Device() {
    }

    public Device(UUID uuid, UUID profile, System system) {
        this.uuid = uuid;
        this.profile = profile;
        this.system = system.name();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getProfile() {
        return profile;
    }

    public void setProfile(UUID profile) {
        this.profile = profile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
