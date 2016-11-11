package pl.edu.agh.student.model.rule.action;


public class SendUrlNotificationAction implements RuleAction {

    private SendUrlNotificationActionConfig config;

    public SendUrlNotificationAction() {
    }

    public SendUrlNotificationActionConfig getConfig() {
        return config;
    }

    public void setConfig(SendUrlNotificationActionConfig config) {
        this.config = config;
    }

    @Override
    public RuleActionExternalType getExternalType() {
        return RuleActionExternalType.U;
    }
}
