package pl.edu.agh.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import pl.edu.agh.student.model.BeaconDiscoverEvent;

@MessageEndpoint
public class BeaconDiscoverProcessor {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

//	@ServiceActivator(inputChannel = "input")
    @StreamListener("input")
    public void onBeaconDiscover(BeaconDiscoverEvent event){

		// @todo find profile by device uuid
		// @todo add profile id to vendor profiles id

		LOG.info("Received" + event);
	}
}