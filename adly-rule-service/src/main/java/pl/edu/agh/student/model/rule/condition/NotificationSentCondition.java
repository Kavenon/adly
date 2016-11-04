package pl.edu.agh.student.model.rule.condition;


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
