package pl.edu.agh.student;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationChannel {

	@Input
    SubscribableChannel input();
}