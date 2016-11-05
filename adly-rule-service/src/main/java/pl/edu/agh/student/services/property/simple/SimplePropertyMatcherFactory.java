package pl.edu.agh.student.services.property.simple;

import pl.edu.agh.student.model.rule.property.type.PropertyType;

public class SimplePropertyMatcherFactory {

    public static ISimplePropertyMatcher getMatcher(PropertyType propertyType){

        switch (propertyType){
            case DATE:
                return new DatePropertyMatcher();
            case INTEGER:
                return new IntegerPropertyMatcher();
            case TEXT:
                return new TextPropertyMatcher();
            default:
                throw new IllegalArgumentException("Requested property type not supported: " + propertyType);
        }

    }
}
