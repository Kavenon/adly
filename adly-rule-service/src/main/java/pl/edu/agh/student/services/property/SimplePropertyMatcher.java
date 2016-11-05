package pl.edu.agh.student.services.property;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;
import pl.edu.agh.student.model.rule.property.type.SimplePropertyType;
import pl.edu.agh.student.services.property.simple.SimplePropertyMatcherFactory;

public class SimplePropertyMatcher implements IPropertyMatcher {

    private SimplePropertyType propertyType;

    public SimplePropertyMatcher(SimplePropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public boolean match(UserProfileConditionCheck userProfileConditionCheck, String actualValue) {

        return SimplePropertyMatcherFactory
                .getMatcher(propertyType.getSimpleType())
                .match(userProfileConditionCheck, actualValue);

    }
}
