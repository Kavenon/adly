package pl.edu.agh.student.services.property.simple;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

public class DatePropertyMatcher implements ISimplePropertyMatcher {

    @Override
    public boolean match(UserProfileConditionCheck check, String value) {

        // @todo: implement later
        switch(check.getOperator()){
            case BEFORE:
            case AFTER:
            case TODAY:
                return true;
            default:
                throw new IllegalArgumentException("Requested operator not allowed for date matcher: " + check.getOperator());
        }
    }

}
