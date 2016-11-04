package pl.edu.agh.student.model.rule.event;


public class BeaconDiscoverEvent implements RuleEvent {

    private BeaconDiscoverEventConfig config;

    public BeaconDiscoverEvent() {
    }

    public BeaconDiscoverEventConfig getConfig() {
        return config;
    }

    public void setConfig(BeaconDiscoverEventConfig config) {
        this.config = config;
    }

}
