package pl.edu.agh.student;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class FcmConnectionProvider implements IFcmConnectionProvider {

    private FcmConnection fcmConnection;
    private static final Log LOG = LogFactory.getLog(FcmConnectionProvider.class);

    public FcmConnectionProvider(
            @Value("${fcm.endpoint}") String fcmEndpoint,
            @Value("${fcm.port}") String fcmPort,
            @Value("${fcm.sender.id}") String fcmSenderId,
            @Value("${fcm.api.key}") String fcmApiKey) {

        fcmConnection =
                new FcmConnection(
                        fcmEndpoint,
                        fcmPort,
                        fcmSenderId,
                        fcmApiKey

                );
        try {
            fcmConnection.connect();
            fcmConnection.login();
        } catch (IOException | XMPPException | SmackException e) {
            LOG.error("Could not connect to fcm", e);
        }

    }

    @Override
    public Optional<FcmConnection> getFcmConnection(){
        if(!fcmConnection.isConnected() || !fcmConnection.isAuthenticated()){
            return Optional.empty();
        }
        return Optional.of(fcmConnection);
    }
}
