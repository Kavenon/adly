package pl.edu.agh.student.model.rule.condition;

import java.time.temporal.ChronoUnit;

public class BeaconDiscoverConditionConfig {

    private Integer beaconId;
    private boolean checkDate = false;

    private Integer timeValue;
    private ChronoUnit timeUnit;

    public Integer getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Integer beaconId) {
        this.beaconId = beaconId;
    }

    public boolean isCheckDate() {
        return checkDate;
    }

    public void setCheckDate(boolean checkDate) {
        this.checkDate = checkDate;
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
        return "BeaconDiscoverConditionConfig{" +
                "timeUnit=" + timeUnit +
                ", timeValue=" + timeValue +
                ", checkDate=" + checkDate +
                ", beaconId=" + beaconId +
                '}';
    }
}
