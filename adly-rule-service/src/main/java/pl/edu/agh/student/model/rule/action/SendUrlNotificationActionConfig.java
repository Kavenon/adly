package pl.edu.agh.student.model.rule.action;

public class SendUrlNotificationActionConfig extends AbstractSendPushAction {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
