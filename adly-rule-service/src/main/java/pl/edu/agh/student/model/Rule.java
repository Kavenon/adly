package pl.edu.agh.student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Type;
import pl.edu.agh.student.model.action.RuleAction;
import pl.edu.agh.student.model.condition.RuleCondition;
import pl.edu.agh.student.model.event.RuleEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Arrays;

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

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private RuleAction[] actions = new RuleAction[]{};

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

    public RuleAction[] getActions() {
        return actions;
    }

    public void setActions(RuleAction[] actions) {
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
                ", actions=" + Arrays.toString(actions) +
                '}';
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper om = new ObjectMapper();
        Rule rule = om.readValue("{\"id\":28,\"name\":\"Rule\",\"userId\":1,\"deleted\":false,\"active\":true,\"events\":[{\"type\":\".BeaconDiscoverEvent\",\"config\":{\"beaconId\":1}}],\"conditions\":[{\"type\":\".UserProfileCondition\",\"config\":{\"checks\":[{\"propertyId\":22,\"operator\":\"EQUAL\",\"value\":\"adfadf\"}]}}],\"actions\":[{\"type\":\".SendPlainPushAction\",\"config\":{\"title\":\"adfadf\",\"text\":\"adfadfadfadf\"}}]}", Rule.class);
        System.out.println(rule);
    }
}
