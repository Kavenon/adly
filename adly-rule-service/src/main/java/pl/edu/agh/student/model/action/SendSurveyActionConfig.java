package pl.edu.agh.student.model.action;

public class SendSurveyActionConfig extends AbstractSendPushAction{

    private Integer surveyId;

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }
}
