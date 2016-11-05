package pl.edu.agh.student.services.matchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.event.BeaconDiscoverEvent;
import pl.edu.agh.student.model.rule.event.RuleEvent;

@Component
public class SpecificEventParamsMatcherFactory {

    @Autowired
    private ISpecificEventParamsMatcher beaconDiscoverEventMatcher;

    public ISpecificEventParamsMatcher getFactory(RuleEvent event) {

        if(event instanceof BeaconDiscoverEvent){
            return beaconDiscoverEventMatcher;
        }

        throw new IllegalArgumentException("Requested event is not supported. " + event);

    }

}
