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

        if(action instanceof SendSurveyAction){
            return sendSurveyActionExecutor;
        }
        else if(action instanceof SendPlainPushAction){
            return sendPlainPushActionExecutor;
        }
        else if(action instanceof SendUrlNotificationAction){
            return sendUrlNotificationActionExecutor;
        }

        throw new IllegalArgumentException("Requested action is not supported. " + action);

    }
}
