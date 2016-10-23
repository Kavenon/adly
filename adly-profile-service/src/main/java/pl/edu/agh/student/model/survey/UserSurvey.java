package pl.edu.agh.student.model.survey;

import javax.persistence.Entity;

@Entity
public class UserSurvey extends Survey {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
