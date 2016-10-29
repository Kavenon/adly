package pl.edu.agh.student;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BeaconChannels {

	@Output
    MessageChannel output();

}