package pl.edu.agh.student.services.property;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

public interface IPropertyMatcher {

    boolean match(UserProfileConditionCheck userProfileConditionCheck, String actualValue);

}
