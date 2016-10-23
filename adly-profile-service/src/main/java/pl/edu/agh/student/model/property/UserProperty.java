package pl.edu.agh.student.model.property;

import javax.persistence.Entity;

@Entity
public class UserProperty extends ProfileProperty {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
