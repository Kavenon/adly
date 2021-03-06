package pl.edu.agh.student.model.survey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Survey implements Serializable {

    private Integer surveyId;
    private List<SurveyField> fieldList;

    public Survey() {

    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public List<SurveyField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<SurveyField> fieldList) {
        this.fieldList = fieldList;
    }
}
