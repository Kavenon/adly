package pl.edu.agh.student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeaconDiscoverEvent {

    private String uuid;
    private String traceId;
    private Beacon beacon;

    public BeaconDiscoverEvent(String uuid, String traceId, Beacon beacon) {
        this.uuid = uuid;
        this.traceId = traceId;
        this.beacon = beacon;
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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "BeaconDiscoverEvent{" +
                "uuid='" + uuid + '\'' +
                ", traceId='" + traceId + '\'' +
                ", beacon=" + beacon +
                '}';
    }
}
