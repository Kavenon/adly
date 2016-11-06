package pl.edu.agh.student.services;

import pl.edu.agh.student.model.rule.action.RuleAction;

public interface IPayloadFetcher {

    public Object payload(RuleAction ruleAction);

}
