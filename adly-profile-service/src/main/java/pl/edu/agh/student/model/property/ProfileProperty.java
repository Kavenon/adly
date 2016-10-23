package pl.edu.agh.student.model.property;

import org.hibernate.annotations.Type;
import pl.edu.agh.student.hibernate.JsonBaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ProfileProperty extends JsonBaseEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private IPropertyType type;

    private Boolean deleted = false;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public IPropertyType getType() {
        return type;
    }

    public void setType(IPropertyType type) {
        this.type = type;
    }

}
