package pl.edu.agh.student.model.rule.condition;


public class UserProfileCondition implements RuleCondition {

    private UserProfileConditionConfig config;

    public UserProfileCondition() {
    }

    public UserProfileConditionConfig getConfig() {
        return config;
    }

    public void setConfig(UserProfileConditionConfig config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "UserProfileCondition{" +
                "config=" + config +
                '}';
    }
}
