package pl.edu.agh.student.model.receipent;

import pl.edu.agh.student.model.System;

import java.util.UUID;

public class Recipient {

    private UUID deviceId;
    private System system;
    private String token;

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "deviceId=" + deviceId +
                ", system=" + system +
                ", token='" + token + '\'' +
                '}';
    }
}

