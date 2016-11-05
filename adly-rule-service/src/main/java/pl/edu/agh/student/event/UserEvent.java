package pl.edu.agh.student.event;


import pl.edu.agh.student.model.rule.event.RuleEvent;

public interface UserEvent {

    String getModelType();
    String getUuid();

    Class<? extends RuleEvent> getRuleEvent();

    Long getUserId();

    String toString();

}
