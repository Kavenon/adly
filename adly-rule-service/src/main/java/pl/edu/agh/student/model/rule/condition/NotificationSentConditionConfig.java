package pl.edu.agh.student.model.rule.condition;

import java.time.temporal.ChronoUnit;

public class NotificationSentConditionConfig {

    private boolean negation;
    private Integer timeValue;
    private ChronoUnit timeUnit;

    public boolean isNegation() {
        return negation;
    }

    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    public Integer getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(Integer timeValue) {
        this.timeValue = timeValue;
    }

    public ChronoUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(ChronoUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public String toString() {
        return "NotificationSentConditionConfig{" +
                "negation=" + negation +
                ", timeValue=" + timeValue +
                ", timeUnit=" + timeUnit +
                '}';
    }
}
