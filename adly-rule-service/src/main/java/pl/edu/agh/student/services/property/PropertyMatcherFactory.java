package pl.edu.agh.student.services.property;

import pl.edu.agh.student.model.rule.property.type.EnumeratedPropertyType;
import pl.edu.agh.student.model.rule.property.type.IPropertyType;
import pl.edu.agh.student.model.rule.property.type.SimplePropertyType;

public class PropertyMatcherFactory {

    public static IPropertyMatcher getMatcher(IPropertyType propertyType){

        if(propertyType instanceof SimplePropertyType){
            return new SimplePropertyMatcher((SimplePropertyType) propertyType);
        }
        else if(propertyType instanceof EnumeratedPropertyType){
            return new EnumeratedPropertyMatcher((EnumeratedPropertyType)propertyType);
        }

        throw new IllegalArgumentException("Requested property type not supported: " + propertyType);

    }

}
