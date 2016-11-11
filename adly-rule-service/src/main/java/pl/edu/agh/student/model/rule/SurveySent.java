package pl.edu.agh.student.model.rule;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("survey_sent")
public class SurveySent {

    @PrimaryKey
    private SurveySentKey surveySentKey;

    public SurveySent() {
    }

    public SurveySent(SurveySentKey surveySentKey) {
        this.surveySentKey = surveySentKey;
    }

    public SurveySentKey getSurveySentKey() {
        return surveySentKey;
    }

    public void setSurveySentKey(SurveySentKey surveySentKey) {
        this.surveySentKey = surveySentKey;
    }

    @Override
    public String toString() {
        return "SurveySent{" +
                "surveySentKey=" + surveySentKey +
                '}';
    }
}
