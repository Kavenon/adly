package pl.edu.agh.student.model.survey;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class Survey {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Boolean deleted = false;

    @OneToMany
    private Set<SurveyField> fieldList;

    public Survey() {
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

    public Set<SurveyField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(Set<SurveyField> fieldList) {
        this.fieldList = fieldList;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fieldList=" + fieldList +
                '}';
    }
}
