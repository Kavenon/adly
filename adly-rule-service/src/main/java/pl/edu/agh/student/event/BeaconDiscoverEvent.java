package pl.edu.agh.student.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.edu.agh.student.model.rule.event.RuleEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeaconDiscoverEvent implements UserEvent {

    private String uuid;
    private Long userId;

    private Beacon beacon;

    public BeaconDiscoverEvent() {
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    public Class<? extends RuleEvent> getRuleEvent(){
        return pl.edu.agh.student.model.rule.event.BeaconDiscoverEvent.class;
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
