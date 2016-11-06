package pl.edu.agh.student.services;

import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.event.RuleEvent;

import java.util.UUID;

public class MockUserEvent implements UserEvent {

    @Override
    public String getModelType() {
        return null;
    }

    @Override
    public String getUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Class<? extends RuleEvent> getRuleEvent() {
        return null;
    }

    @Override
    public Long getUserId() {
        return null;
    }

}
