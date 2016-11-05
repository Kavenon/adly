package pl.edu.agh.student.services.property;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;
import pl.edu.agh.student.model.rule.property.type.EnumeratedPropertyType;

public class EnumeratedPropertyMatcher implements IPropertyMatcher {

    private EnumeratedPropertyType propertyType;

    public EnumeratedPropertyMatcher(EnumeratedPropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public boolean match(UserProfileConditionCheck userProfileConditionCheck, String actualValue) {
        // todo: implement ui first
        return true;

    }
}
