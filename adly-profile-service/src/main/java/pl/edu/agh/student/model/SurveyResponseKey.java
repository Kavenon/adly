package pl.edu.agh.student.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@PrimaryKeyClass
public class SurveyResponseKey implements Serializable {

    @PrimaryKeyColumn(name = "profile_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID profileId;

    @PrimaryKeyColumn(name = "survey_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Integer surveyId;

    @PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private Date date;


    public SurveyResponseKey() {
    }

    public SurveyResponseKey(UUID profileId, Date date, Integer surveyId) {
        this.profileId = profileId;
        this.date = date;
        this.surveyId = surveyId;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public String toString() {
        return "SurveyResponseKey{" +
                "surveyId=" + surveyId +
                ", date=" + date +
                ", profileId=" + profileId +
                '}';
    }
}
