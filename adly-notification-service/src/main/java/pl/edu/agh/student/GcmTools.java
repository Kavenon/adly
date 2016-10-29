package pl.edu.agh.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GcmTools {
    public static ObjectMapper om = new ObjectMapper();

    public static String createJsonMessage(String to, String messageId,
                                           String payload) {
        Map<String, Object> message = new HashMap<>();
        message.put("to", to);
        message.put("message_id", messageId);
        try {
            ObjectNode json = (ObjectNode) om.readTree(payload);
            message.put("data", json);
            return om.writeValueAsString(message);
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public static String nextMessageId() {
        return "m-" + UUID.randomUUID().toString();
    }
}
