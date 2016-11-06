package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.RuleActionEntity;
import pl.edu.agh.student.repository.RuleActionRepository;

@Component
public class RuleActionService {

    private RuleActionRepository repository;
    private PayloadFetcherFactory payloadFetcherFactory;

    @Autowired
    public RuleActionService(RuleActionRepository repository, PayloadFetcherFactory payloadFetcherFactory) {
        this.repository = repository;
        this.payloadFetcherFactory = payloadFetcherFactory;
    }

    public Object getActionPayload(Integer actionId){

        RuleActionEntity one = repository.findOne(actionId);

        if(one != null){
            return payloadFetcherFactory.getFetcher(one.getRuleAction()).payload(one.getRuleAction());
        }
        else {
            throw new IllegalArgumentException("Requested action does not exist");
        }

    }



}
