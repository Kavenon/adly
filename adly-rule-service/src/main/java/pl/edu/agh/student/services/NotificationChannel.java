package pl.edu.agh.student.services;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface NotificationChannel {

	@Output
    SubscribableChannel output();
}