package pl.edu.agh.student.services.payload;

import pl.edu.agh.student.model.EmptyExternalPayload;
import pl.edu.agh.student.model.IExternalPayload;
import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.model.rule.action.RuleActionExternalType;
import pl.edu.agh.student.services.IPayloadFetcher;

public class EmptyPayloadFetcher implements IPayloadFetcher {

    @Override
    public IExternalPayload payload(RuleAction ruleAction) {
        return new EmptyExternalPayload(RuleActionExternalType.P);
    }

}
