package pl.edu.agh.student.services.payload;

import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.services.IPayloadFetcher;

public class ConfigPayloadFetcher implements IPayloadFetcher {
    @Override
    public Object payload(RuleAction ruleAction) {
        return ruleAction.getConfig();
    }
}
