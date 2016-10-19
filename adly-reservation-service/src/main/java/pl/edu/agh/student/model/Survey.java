package pl.edu.agh.student.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
public class Survey {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Instant createdOn;

    @LastModifiedDate
    private Instant modifiedOn;

    @Version
    private Integer version;

    private String name;
    private Long userId;

    @OneToMany
    private List<SurveyField> fieldList;

    public Survey() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public List<SurveyField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<SurveyField> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", fieldList=" + fieldList +
                '}';
    }
}
