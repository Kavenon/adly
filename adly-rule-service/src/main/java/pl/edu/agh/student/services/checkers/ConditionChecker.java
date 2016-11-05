package pl.edu.agh.student.services.checkers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.services.matchers.EventParamsMatcher;

import java.util.Arrays;
import java.util.List;

@Component
public class ConditionChecker {

    private static final Log LOG = LogFactory.getLog(EventParamsMatcher.class);

    private SpecificConditionCheckedFactory specificConditionCheckedFactory;

    @Autowired
    public ConditionChecker(SpecificConditionCheckedFactory specificConditionCheckedFactory) {
        this.specificConditionCheckedFactory = specificConditionCheckedFactory;
    }

    public boolean check(Rule rule, UserEvent userEvent) {

        List<RuleCondition> ruleConditions = Arrays.asList(rule.getConditions());

        return ruleConditions
                .stream()
                .allMatch(condition -> checkIfConditionOk(userEvent, condition));

    }

    private boolean checkIfConditionOk(UserEvent userEvent, RuleCondition condition) {
        boolean match = specificConditionCheckedFactory.getFactory(condition).check(condition, userEvent);
        if(match){
            LOG.info("Condition matched: " + condition + " for user event: " + userEvent);
        }
        else {
            LOG.info("Condition not matched: " + condition + " for user event: " + userEvent);
        }
        return match;
    }

}
