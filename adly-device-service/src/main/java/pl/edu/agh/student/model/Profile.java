package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table
public class Profile {

    @PrimaryKey
    private UUID uuid;

    private List<UUID> devices = new ArrayList<>();

    public Profile() {
    }

    public Profile(UUID uuid, List<UUID> devices) {
        this.uuid = uuid;
        this.devices = devices;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<UUID> getDevices() {
        return devices;
    }

    public void setDevices(List<UUID> devices) {
        this.devices = devices;
    }
}
