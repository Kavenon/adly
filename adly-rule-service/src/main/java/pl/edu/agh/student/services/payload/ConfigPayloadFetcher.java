package pl.edu.agh.student.services.payload;

import pl.edu.agh.student.model.ExternalPayload;
import pl.edu.agh.student.model.IExternalPayload;
import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.services.IPayloadFetcher;

public class ConfigPayloadFetcher implements IPayloadFetcher {

    @Override
    public IExternalPayload payload(RuleAction ruleAction) {
        return new ExternalPayload(ruleAction.getExternalType(),ruleAction.getConfig());
    }

}
