package pl.edu.agh.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeaconDiscoverEvent {

    private String uuid;
    private String gummy;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGummy() {
        return gummy;
    }

    public void setGummy(String gummy) {
        this.gummy = gummy;
    }

    @Override
    public String toString() {
        return "BeaconDiscoverEvent{" +
                "uuid='" + uuid + '\'' +
                ", gummy='" + gummy + '\'' +
                '}';
    }
}
