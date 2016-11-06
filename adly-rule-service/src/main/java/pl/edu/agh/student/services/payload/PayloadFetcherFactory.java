package pl.edu.agh.student.services.payload;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.model.rule.action.SendPlainPushAction;
import pl.edu.agh.student.model.rule.action.SendSurveyAction;
import pl.edu.agh.student.model.rule.action.SendUrlNotificationAction;
import pl.edu.agh.student.services.IPayloadFetcher;

@Component
public class PayloadFetcherFactory {

    private IPayloadFetcher surveyPayloadFetcher;

    public PayloadFetcherFactory(IPayloadFetcher surveyPayloadFetcher) {
        this.surveyPayloadFetcher = surveyPayloadFetcher;
    }

    public IPayloadFetcher getFetcher(RuleAction ruleAction){

        if(ruleAction instanceof SendPlainPushAction){
            return new EmptyPayloadFetcher();
        }
        else if(ruleAction instanceof SendUrlNotificationAction){
            return new ConfigPayloadFetcher();
        }
        else if(ruleAction instanceof SendSurveyAction){
            return surveyPayloadFetcher;
        }

        throw new IllegalArgumentException("Requested fetcher not allowed");
    }

}
