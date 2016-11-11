package pl.edu.agh.student.model;

import pl.edu.agh.student.model.rule.action.RuleActionExternalType;

public class ExternalPayload implements IExternalPayload {

    private RuleActionExternalType type;
    private Object payload;

    public ExternalPayload(RuleActionExternalType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public RuleActionExternalType getType() {
        return type;
    }

    public void setType(RuleActionExternalType type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
