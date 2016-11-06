package pl.edu.agh.student.services.property.simple;

import org.junit.Test;
import pl.edu.agh.student.model.rule.condition.ConditionOperator;
import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerPropertyMatcherTest {

    private IntegerPropertyMatcher matcher = new IntegerPropertyMatcher();

    @Test
    public void matchEqualTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.EQUAL, "1");
        assertThat(matcher.match(check, "1")).isTrue();
    }

    @Test
    public void matchEqualFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.EQUAL, "2");
        assertThat(matcher.match(check, "1")).isFalse();
    }

    @Test
    public void matchNotEqualTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "2");
        assertThat(matcher.match(check, "1")).isTrue();
    }

    @Test
    public void matchNotEqualFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "1");
        assertThat(matcher.match(check, "1")).isFalse();
    }

    @Test
    public void matchLessThanTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "1");
        assertThat(matcher.match(check, "0")).isTrue();
    }

    @Test
    public void matchLessThanFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "1");
        assertThat(matcher.match(check, "2")).isTrue();
    }

    @Test
    public void matchMoreThanTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "1");
        assertThat(matcher.match(check, "2")).isTrue();
    }

    @Test
    public void matchMoreThanFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "1");
        assertThat(matcher.match(check, "0")).isTrue();
    }

    private UserProfileConditionCheck getCheck(ConditionOperator equal, String value) {
        UserProfileConditionCheck userProfileConditionCheck = new UserProfileConditionCheck();
        userProfileConditionCheck.setOperator(equal);
        userProfileConditionCheck.setValue(value);
        return userProfileConditionCheck;
    }

}