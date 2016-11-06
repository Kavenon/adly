package pl.edu.agh.student.services.property.simple;

import org.junit.Test;
import pl.edu.agh.student.model.rule.condition.ConditionOperator;
import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;

import static org.assertj.core.api.Assertions.assertThat;

public class TextPropertyMatcherTest {

    private TextPropertyMatcher matcher = new TextPropertyMatcher();

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
    public void matchNotEqualFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "1");
        assertThat(matcher.match(check, "1")).isFalse();
    }

    @Test
    public void matchNotEqualTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_EQUAL, "2");
        assertThat(matcher.match(check, "1")).isTrue();
    }

    @Test
    public void matchContainsTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.CONTAINS, "dude");
        assertThat(matcher.match(check, "hey dude")).isTrue();
    }

    @Test
    public void matchContainsFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.CONTAINS, "dude");
        assertThat(matcher.match(check, "hey")).isFalse();
    }

    @Test
    public void matchNotContainsFalse() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_CONTAINS, "dude");
        assertThat(matcher.match(check, "hey dude")).isFalse();
    }

    @Test
    public void matchNotContainsTrue() throws Exception {
        UserProfileConditionCheck check = getCheck(ConditionOperator.NOT_CONTAINS, "dude");
        assertThat(matcher.match(check, "hey")).isTrue();
    }


    private UserProfileConditionCheck getCheck(ConditionOperator equal, String value) {
        UserProfileConditionCheck userProfileConditionCheck = new UserProfileConditionCheck();
        userProfileConditionCheck.setOperator(equal);
        userProfileConditionCheck.setValue(value);
        return userProfileConditionCheck;
    }
}