package pl.edu.agh.student.model.rule.condition;

public class UserProfileConditionCheck {

    private Integer propertyId;
    private ConditionOperator operator;
    private Object value;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
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
