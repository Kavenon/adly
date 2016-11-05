package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.RuleActionEntity;
import pl.edu.agh.student.repository.RuleActionRepository;

@Component
public class RuleActionService {

    private RuleActionRepository repository;

    @Autowired
    public RuleActionService(RuleActionRepository repository) {
        this.repository = repository;
    }

    public Object getActionPayload(Integer actionId){

        RuleActionEntity one = repository.findOne(actionId);

        if(one != null){
            return one.getRuleAction();
        }
        else {
            throw new IllegalArgumentException("Requested action does not exist");
        }

    }



}
