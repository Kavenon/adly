package pl.edu.agh.student.services.catchers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import pl.edu.agh.student.event.BeaconDiscoverUserEvent;
import pl.edu.agh.student.services.EventHandler;

@MessageEndpoint
public class BeaconDiscoverProcessor {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private EventHandler eventHandler;

    @Autowired
    public BeaconDiscoverProcessor(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @StreamListener("input")
    public void onBeaconDiscover(BeaconDiscoverUserEvent event){

        LOG.info("Received beacon discover event " + event);
//        eventHandler.handleEvent(event);

	}
}