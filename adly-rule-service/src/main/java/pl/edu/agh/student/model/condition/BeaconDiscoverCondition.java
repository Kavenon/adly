package pl.edu.agh.student.model.condition;


public class BeaconDiscoverCondition implements RuleCondition {

    private BeaconDiscoverConditionConfig config;

    public BeaconDiscoverCondition() {
    }

    public BeaconDiscoverConditionConfig getConfig() {
        return config;
    }

    public void setConfig(BeaconDiscoverConditionConfig config) {
        this.config = config;
    }

}
