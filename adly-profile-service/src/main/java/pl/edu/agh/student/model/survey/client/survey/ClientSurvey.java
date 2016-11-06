package pl.edu.agh.student.model.survey.client.survey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientSurvey implements Serializable {

    private Integer surveyId;
    private List<ClientSurveyField> fieldList;

    public ClientSurvey() {

    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public List<ClientSurveyField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<ClientSurveyField> fieldList) {
        this.fieldList = fieldList;
    }
}
