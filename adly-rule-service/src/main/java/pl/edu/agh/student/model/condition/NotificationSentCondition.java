package pl.edu.agh.student.model.condition;


public class NotificationSentCondition implements RuleCondition {

    private NotificationSentConditionConfig config;

    public NotificationSentCondition() {
    }

    public NotificationSentConditionConfig getConfig() {
        return config;
    }

    public void setConfig(NotificationSentConditionConfig config) {
        this.config = config;
    }

}
