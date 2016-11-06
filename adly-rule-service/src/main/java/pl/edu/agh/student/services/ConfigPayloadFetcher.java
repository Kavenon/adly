package pl.edu.agh.student.services;

import pl.edu.agh.student.model.rule.action.RuleAction;

public class ConfigPayloadFetcher implements IPayloadFetcher {
    @Override
    public Object payload(RuleAction ruleAction) {
        return ruleAction.getConfig();
    }
}
