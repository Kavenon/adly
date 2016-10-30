package pl.edu.agh.student;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import pl.edu.agh.student.model.BeaconDiscoverEvent;

@MessagingGateway
public interface BeaconWriter {

	@Gateway(requestChannel = "output")
    void write(BeaconDiscoverEvent event);

}
