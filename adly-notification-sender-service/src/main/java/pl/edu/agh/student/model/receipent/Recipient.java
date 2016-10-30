package pl.edu.agh.student.model.receipent;
import pl.edu.agh.student.model.System;

public class Recipient {

    private System system;
    private String token;

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
                "system=" + system +
                ", token='" + token + '\'' +
                '}';
    }
}

