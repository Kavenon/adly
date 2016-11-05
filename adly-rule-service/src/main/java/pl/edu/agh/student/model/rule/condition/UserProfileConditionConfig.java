package pl.edu.agh.student.model.rule.condition;

import java.util.ArrayList;
import java.util.List;

public class UserProfileConditionConfig extends BaseCondition {

    private List<UserProfileConditionCheck> checks = new ArrayList<>();

    public List<UserProfileConditionCheck> getChecks() {
        return checks;
    }

    public void setChecks(List<UserProfileConditionCheck> checks) {
        this.checks = checks;
    }

    @Override
    public String toString() {
        return "UserProfileConditionConfig{" +
                "checks=" + checks +
                '}';
    }
}
