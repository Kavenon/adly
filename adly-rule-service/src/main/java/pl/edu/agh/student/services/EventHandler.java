package pl.edu.agh.student.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.repository.RuleRepository;
import pl.edu.agh.student.services.checkers.ConditionChecker;
import pl.edu.agh.student.services.executors.ActionExecutor;
import pl.edu.agh.student.services.matchers.EventParamsMatcher;

import java.util.List;

@Component
public class EventHandler {

    private static final Log LOG = LogFactory.getLog(EventHandler.class);

    private RuleRepository ruleRepository;
    private EventParamsMatcher eventParamsMatcher;
    private ConditionChecker conditionChecker;
    private ActionExecutor actionExecutor;

    @Autowired
    public EventHandler(RuleRepository ruleRepository, EventParamsMatcher eventParamsMatcher, ConditionChecker conditionChecker, ActionExecutor actionExecutor) {
        this.ruleRepository = ruleRepository;
        this.eventParamsMatcher = eventParamsMatcher;
        this.conditionChecker = conditionChecker;
        this.actionExecutor = actionExecutor;
    }

    public void handleEvent(UserEvent userEvent){

        long startMillis = System.currentTimeMillis();

        LOG.info("Received event: " + userEvent);

        List<Rule> activeRulesForEvent =
                ruleRepository.findByUserIdAndActiveAndEventType(userEvent.getUserId(), true, userEvent.getModelType());

        LOG.info("Found rules: " + activeRulesForEvent.size());

        activeRulesForEvent
                .stream()
                .filter(rule -> eventParamsMatcher.match(rule, userEvent))
                .filter(rule -> conditionChecker.check(rule, userEvent))
                .forEach(rule -> actionExecutor.execute(rule, userEvent));

        LOG.info("Processing rules finished in: " + (System.currentTimeMillis() - startMillis));

        //@todo:
        /*
         *   *** 1. wyciagnac z bazy by eventType
         *   2. przegladnac petla elementy i sprawdzic czy parametry z bazy odpowiadaja parametrom eventu usera
         *   ( zamknac to w jakiejs osobnej klasie)
         *   3. Dla kazdego warunku zrobić evaluate (osobne komponenty w zaleznosci od typu warunku)
         *   4. Dla każdej akcji wykonać execute ( np. SendPush executor rzuci na kolejke push)
         *   5. done (+sprawdzic, czy push payload jest dostepny z zewnatrz, a pewnie nie jest bo nie zapisuje nigdzie pusha na bazie)
         */
    }

}
