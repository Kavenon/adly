package pl.edu.agh.student.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SurveyField {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated
    private SurveyFieldType type;

    public SurveyField() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SurveyFieldType getType() {
        return type;
    }

    public void setType(SurveyFieldType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SurveyField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
