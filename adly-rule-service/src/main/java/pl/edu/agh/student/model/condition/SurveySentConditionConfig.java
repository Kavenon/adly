package pl.edu.agh.student.model.condition;

import java.time.temporal.ChronoUnit;

public class SurveySentConditionConfig {

    private Integer surveyId;
    private Integer timeValue;
    private ChronoUnit timeUnit;

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(Integer timeValue) {
        this.timeValue = timeValue;
    }

    public ChronoUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(ChronoUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public String toString() {
        return "SurveySentConditionConfig{" +
                "surveyId=" + surveyId +
                ", timeValue=" + timeValue +
                ", timeUnit=" + timeUnit +
                '}';
    }
}
