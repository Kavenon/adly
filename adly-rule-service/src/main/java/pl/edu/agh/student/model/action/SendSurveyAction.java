package pl.edu.agh.student.model.action;


public class SendSurveyAction implements RuleAction {

    private SendSurveyActionConfig config;

    public SendSurveyAction() {
    }

    public SendSurveyActionConfig getConfig() {
        return config;
    }

    public void setConfig(SendSurveyActionConfig config) {
        this.config = config;
    }

}
