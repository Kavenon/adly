package pl.edu.agh.student;

import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;

public class FcmPacketExtension extends DefaultPacketExtension {

    private final String json;
    private static final String GCM_ELEMENT_NAME = "gcm";
    private static final String GCM_NAMESPACE = "google:mobile:data";

    public FcmPacketExtension(String json) {
        super(GCM_ELEMENT_NAME, GCM_NAMESPACE);
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    @Override
    public String toXML() {
        return String.format("<%s xmlns=\"%s\">%s</%s>",
                GCM_ELEMENT_NAME, GCM_NAMESPACE,
                StringUtils.escapeForXML(json), GCM_ELEMENT_NAME);
    }

    public Packet toPacket() {
        Message message = new Message();
        message.addExtension(this);
        return message;
    }
}
