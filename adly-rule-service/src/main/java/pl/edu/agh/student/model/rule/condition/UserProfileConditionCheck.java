package pl.edu.agh.student.model.rule.condition;

public class UserProfileConditionCheck {

    private Integer propertyId;
    private ConditionOperator operator;
    private String value;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public ConditionOperator getOperator() {
        return operator;
    }

    public void setOperator(ConditionOperator operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserProfileConditionCheck{" +
                "propertyId=" + propertyId +
                ", operator=" + operator +
                ", value=" + value +
                '}';
    }
}
