package pl.edu.agh.student.model.rule;

import org.hibernate.annotations.Type;
import pl.edu.agh.student.model.rule.action.RuleAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RuleActionEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private RuleAction ruleAction;

    public RuleActionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RuleAction getRuleAction() {
        return ruleAction;
    }

    public void setRuleAction(RuleAction ruleAction) {
        this.ruleAction = ruleAction;
    }

    @Override
    public String toString() {
        return "RuleActionEntity{" +
                "id=" + id +
                ", ruleAction=" + ruleAction +
                '}';
    }
}
