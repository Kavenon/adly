package pl.edu.agh.student.model.survey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SurveyField {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Integer profilePropertyId;

    public SurveyField() {
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

    public Integer getProfilePropertyId() {
        return profilePropertyId;
    }

    public void setProfilePropertyId(Integer profilePropertyId) {
        this.profilePropertyId = profilePropertyId;
    }

    @Override
    public String toString() {
        return "SurveyField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profilePropertyId=" + profilePropertyId +
                '}';
    }
}
