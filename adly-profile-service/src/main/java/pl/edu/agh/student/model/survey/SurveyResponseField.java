package pl.edu.agh.student.model.survey;

public class SurveyResponseField {

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SurveyResponseField{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
