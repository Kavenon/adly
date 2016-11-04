package pl.edu.agh.student.model.rule.condition;

import java.time.temporal.ChronoUnit;

public class NotificationSentConditionConfig {

    private Integer timeValue;
    private ChronoUnit timeUnit;

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
        return "BeaconDiscoverConditionConfig{" +
                "timeUnit=" + timeUnit +
                ", timeValue=" + timeValue +
                '}';
    }
}
