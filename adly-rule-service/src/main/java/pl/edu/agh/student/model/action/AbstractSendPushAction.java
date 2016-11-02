package pl.edu.agh.student.model.action;

public abstract class AbstractSendPushAction  {

    protected String title;
    protected String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
