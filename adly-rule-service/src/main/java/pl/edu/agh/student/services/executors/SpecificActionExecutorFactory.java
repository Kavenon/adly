package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.model.rule.action.SendPlainPushAction;
import pl.edu.agh.student.model.rule.action.SendSurveyAction;
import pl.edu.agh.student.model.rule.action.SendUrlNotificationAction;

@Component
public class SpecificActionExecutorFactory {

    @Autowired
    private ISpecificActionExecutor sendSurveyActionExecutor;

    @Autowired
    private ISpecificActionExecutor sendPlainPushActionExecutor;

    @Autowired
    private ISpecificActionExecutor sendUrlNotificationActionExecutor;

    public ISpecificActionExecutor getFactory(RuleAction action) {

        if(action.getClass().isInstance(SendSurveyAction.class)){
            return sendSurveyActionExecutor;
        }
        else if(action.getClass().isInstance(SendPlainPushAction.class)){
            return sendPlainPushActionExecutor;
        }
        else if(action.getClass().isInstance(SendUrlNotificationAction.class)){
            return sendUrlNotificationActionExecutor;
        }

        throw new IllegalArgumentException("Requested action is not supported. " + action);

    }
}
