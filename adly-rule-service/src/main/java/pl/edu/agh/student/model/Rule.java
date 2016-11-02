package pl.edu.agh.student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.deploy.security.ruleset.RuleAction;
import pl.edu.agh.student.model.condition.RuleCondition;
import pl.edu.agh.student.model.event.RuleEvent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rule {

    @Id
    @GeneratedValue
    private Integer id;

    private Long userId;

    private boolean deleted = false;
    private boolean active = false;
    private List<RuleEvent> events = new ArrayList<>();
    private List<RuleCondition> conditions = new ArrayList<>();
    private List<RuleAction> actions = new ArrayList<>();

    public Rule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RuleEvent> getEvents() {
        return events;
    }

    public void setEvents(List<RuleEvent> events) {
        this.events = events;
    }

    public List<RuleCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<RuleCondition> conditions) {
        this.conditions = conditions;
    }

    public List<RuleAction> getActions() {
        return actions;
    }

    public void setActions(List<RuleAction> actions) {
        this.actions = actions;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", userId=" + userId +
                ", active=" + active +
                ", events=" + events +
                ", conditions=" + conditions +
                ", actions=" + actions +
                '}';
    }
}
