package pl.edu.agh.student.model;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import pl.edu.agh.student.hibernate.JsonBinaryType;
import pl.edu.agh.student.hibernate.JsonStringType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public abstract class ProfileProperty  {

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
