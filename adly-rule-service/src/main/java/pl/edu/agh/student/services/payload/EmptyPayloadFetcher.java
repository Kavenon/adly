package pl.edu.agh.student.services.payload;

import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.services.IPayloadFetcher;

public class EmptyPayloadFetcher implements IPayloadFetcher {
    @Override
    public Object payload(RuleAction ruleAction) {
        return new Object();
    }
}
