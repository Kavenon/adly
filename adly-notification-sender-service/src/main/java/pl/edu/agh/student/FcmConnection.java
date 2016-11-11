package pl.edu.agh.student;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

public class FcmConnection {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private XMPPConnection connection;

    private String server;
    private String port;
    private String senderId;
    private String apiKey;

    public FcmConnection(String server, String port, String senderId, String apiKey) {
        this.server = server;
        this.port = port;
        this.senderId = senderId;
        this.apiKey = apiKey;
    }


    public void connect() throws IOException, XMPPException, SmackException {
        ConnectionConfiguration config =
                new ConnectionConfiguration(server, Integer.valueOf(port));
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        config.setReconnectionAllowed(true);
        config.setRosterLoadedAtLogin(false);
        config.setSendPresence(false);
        config.setSocketFactory(SSLSocketFactory.getDefault());

        connection = new XMPPTCPConnection(config);
        connection.connect();

        // Log all outgoing packets
        connection.addPacketInterceptor(packet -> log.info("Sent: " + packet.toXML()), new PacketTypeFilter(Message.class));

    }

    public void login() throws IOException, XMPPException, SmackException {
        connection.login(senderId + "@" + server, apiKey);
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public void send(String jsonRequest) throws SmackException.NotConnectedException {
        Packet request = new FcmPacketExtension(jsonRequest).toPacket();
        connection.sendPacket(request);
    }

    public void disconnect() throws SmackException.NotConnectedException {
        connection.disconnect();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public boolean isAuthenticated() {
        return connection.isAuthenticated();
    }


}
