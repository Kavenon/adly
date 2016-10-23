package pl.edu.agh.student.model.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimplePropertyType implements IPropertyType {

    private PropertyType simpleType;

    public SimplePropertyType() {
    }

    public SimplePropertyType(PropertyType simpleType) {
        this.simpleType = simpleType;
    }

    public PropertyType getSimpleType() {
        return simpleType;
    }

    public void setSimpleType(PropertyType simpleType) {
        this.simpleType = simpleType;
    }
}
