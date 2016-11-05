package pl.edu.agh.student.services.catchers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BeaconChannels {

	@Input
    SubscribableChannel input();
}