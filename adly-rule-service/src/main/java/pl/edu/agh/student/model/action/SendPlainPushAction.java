package pl.edu.agh.student.model.action;

public class SendPlainPushAction implements RuleAction {

    private SendPlainPushActionConfig config;

    public SendPlainPushAction() {
    }

    public SendPlainPushActionConfig getConfig() {
        return config;
    }

    public void setConfig(SendPlainPushActionConfig config) {
        this.config = config;
    }

}
