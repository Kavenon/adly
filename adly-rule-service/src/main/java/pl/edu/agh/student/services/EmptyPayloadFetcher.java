package pl.edu.agh.student.services;

import pl.edu.agh.student.model.rule.action.RuleAction;

public class EmptyPayloadFetcher implements IPayloadFetcher {
    @Override
    public Object payload(RuleAction ruleAction) {
        return new Object();
    }
}
