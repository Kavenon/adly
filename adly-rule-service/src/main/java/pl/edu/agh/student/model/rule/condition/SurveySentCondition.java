package pl.edu.agh.student.model.rule.condition;


public class SurveySentCondition implements RuleCondition {

    private SurveySentConditionConfig config;

    public SurveySentCondition() {
    }

    public SurveySentConditionConfig getConfig() {
        return config;
    }

    public void setConfig(SurveySentConditionConfig config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "SurveySentCondition{" +
                "config=" + config +
                '}';
    }
}
