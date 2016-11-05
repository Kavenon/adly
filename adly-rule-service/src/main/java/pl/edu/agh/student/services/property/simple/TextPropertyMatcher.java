package pl.edu.agh.student.services.property.simple;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

import java.util.Objects;

public class TextPropertyMatcher implements ISimplePropertyMatcher {

    @Override
    public boolean match(UserProfileConditionCheck check, String value) {

        String checkStringValue = String.valueOf(check.getValue());

        switch(check.getOperator()){
            case EQUAL:
                return Objects.equals(checkStringValue, value);
            case NOT_EQUAL:
                return !Objects.equals(checkStringValue, value);
            case CONTAINS:
                return safeContains(value, checkStringValue);
            case NOT_CONTAINS:
                return !safeContains(value, checkStringValue);
            default:
                throw new IllegalArgumentException("Requested operator not allowed for TextPropertyMatcher: " + check.getOperator());
        }

    }

    private boolean safeContains(String base, String needle) {
        if(base == null || base.length() == 0){
            return false;
        }
        else {
            return base.contains(needle);
        }
    }

}
