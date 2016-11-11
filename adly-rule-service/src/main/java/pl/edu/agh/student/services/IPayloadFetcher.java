package pl.edu.agh.student.services;

import pl.edu.agh.student.model.IExternalPayload;
import pl.edu.agh.student.model.rule.action.RuleAction;

public interface IPayloadFetcher {

    IExternalPayload payload(RuleAction ruleAction);

}
