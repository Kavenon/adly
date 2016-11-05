package pl.edu.agh.student.services.property.simple;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

public interface ISimplePropertyMatcher {

    boolean match(UserProfileConditionCheck userProfileConditionCheck, String actualValue);

}
