package pl.edu.agh.student.model;

import pl.edu.agh.student.model.rule.action.RuleActionExternalType;

public class EmptyExternalPayload implements IExternalPayload {

    private RuleActionExternalType type;

    public EmptyExternalPayload(RuleActionExternalType type) {
        this.type = type;
    }

    public RuleActionExternalType getType() {
        return type;
    }

    public void setType(RuleActionExternalType type) {
        this.type = type;
    }
}
