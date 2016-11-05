package pl.edu.agh.student.model.rule.condition;

public abstract class BaseCondition {

    protected boolean negation;

    public boolean isNegation() {
        return negation;
    }

    public void setNegation(boolean negation) {
        this.negation = negation;
    }
}
