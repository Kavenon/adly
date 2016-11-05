package pl.edu.agh.student.model.rule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.model.rule.event.RuleEvent;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rule {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Long userId;

    private boolean deleted = false;
    private boolean active = false;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private RuleEvent[] events = new RuleEvent[]{};

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private RuleCondition[] conditions = new RuleCondition[]{};

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RuleActionEntity> actions = new HashSet<>();

    public Rule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public RuleEvent[] getEvents() {
        return events;
    }

    public void setEvents(RuleEvent[] events) {
        this.events = events;
    }

    public RuleCondition[] getConditions() {
        return conditions;
    }

    public void setConditions(RuleCondition[] conditions) {
        this.conditions = conditions;
    }

    public Set<RuleActionEntity> getActions() {
        return actions;
    }

    public void setActions(Set<RuleActionEntity> actions) {
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
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", deleted=" + deleted +
                ", active=" + active +
                ", events=" + Arrays.toString(events) +
                ", conditions=" + Arrays.toString(conditions) +
                ", actions=" + actions +
                '}';
    }

}
