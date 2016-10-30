package pl.edu.agh.student.model.survey;

import java.util.List;

public class SurveyResponseRequest {

    private Integer surveyId;
    private String uuid;
    private List<SurveyResponseField> fieldList;

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<SurveyResponseField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<SurveyResponseField> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        return "SurveyResponseRequest{" +
                "surveyId=" + surveyId +
                ", uuid='" + uuid + '\'' +
                ", fieldList=" + fieldList +
                '}';
    }
}
