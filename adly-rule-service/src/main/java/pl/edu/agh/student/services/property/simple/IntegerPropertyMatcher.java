package pl.edu.agh.student.services.property.simple;

import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

import java.util.Objects;

public class IntegerPropertyMatcher implements ISimplePropertyMatcher {

    @Override
    public boolean match(UserProfileConditionCheck check, String value) {

        Integer checkIntegerValue = Integer.valueOf(check.getValue());
        Integer integerValue = Integer.valueOf(value);

        switch (check.getOperator()){
            case EQUAL:
                return Objects.equals(checkIntegerValue, integerValue);
            case NOT_EQUAL:
                return !Objects.equals(checkIntegerValue, integerValue);
            case LESS_THAN:
                return integerValue < checkIntegerValue;
            case MORE_THAN:
                return integerValue > checkIntegerValue;
            default:
                throw new IllegalArgumentException("Requested operator not allowed for integer matcher: " + check.getOperator());

        }

    }

}
