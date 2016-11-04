package pl.edu.agh.student.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeaconDiscoverEvent implements UserEvent {

    private String uuid;
    private Long userId;

    private Beacon beacon;

    public BeaconDiscoverEvent() {
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getModelType() {
        return ".BeaconDiscoverEvent";
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    @Override
    public String toString() {
        return "BeaconDiscoverEvent{" +
                "uuid='" + uuid + '\'' +
                ", beacon=" + beacon +
                '}';
    }
}
