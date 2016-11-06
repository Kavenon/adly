package pl.edu.agh.student.model.survey.client.survey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.edu.agh.student.model.property.IPropertyType;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientSurveyField implements Serializable {

    private Integer fieldId;
    private String fieldName;
    private IPropertyType fieldType;

    public ClientSurveyField() {
    }

    public ClientSurveyField(Integer fieldId, String fieldName, IPropertyType fieldType) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public IPropertyType getFieldType() {
        return fieldType;
    }

    public void setFieldType(IPropertyType fieldType) {
        this.fieldType = fieldType;
    }
}
