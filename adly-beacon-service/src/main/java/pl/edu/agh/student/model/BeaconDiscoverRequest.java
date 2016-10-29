package pl.edu.agh.student.model;

import java.util.UUID;

public class BeaconDiscoverRequest {

    private String uuid;
    private String uid;
    private Integer major;
    private Integer minor;
    private String puuid;

    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getMinor() {
        return minor;
    }

    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    @Override
    public String toString() {
        return "BeaconDiscoverRequest{" +
                "puuid='" + puuid + '\'' +
                ", minor=" + minor +
                ", major=" + major +
                ", uid='" + uid + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
