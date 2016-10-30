package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("survey_response")
public class SurveyResponse {

    @PrimaryKey
    private SurveyResponseKey surveyResponseKey;

    private String response;

    @Column("device_id")
    private UUID device;

    public SurveyResponse() {
    }

    public SurveyResponse(SurveyResponseKey surveyResponseKey, String response, UUID device) {
        this.surveyResponseKey = surveyResponseKey;
        this.response = response;
        this.device = device;
    }

    public SurveyResponseKey getSurveyResponseKey() {
        return surveyResponseKey;
    }

    public void setSurveyResponseKey(SurveyResponseKey surveyResponseKey) {
        this.surveyResponseKey = surveyResponseKey;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UUID getDevice() {
        return device;
    }

    public void setDevice(UUID device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "SurveyResponse{" +
                "device=" + device +
                ", response='" + response + '\'' +
                ", surveyResponseKey=" + surveyResponseKey +
                '}';
    }
}
