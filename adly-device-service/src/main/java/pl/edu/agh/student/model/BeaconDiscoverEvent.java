package pl.edu.agh.student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeaconDiscoverEvent {

    private String uuid;
    private Beacon beacon;

    public BeaconDiscoverEvent() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
