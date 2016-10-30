package pl.edu.agh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(NotificationChannel.class)
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class AdlyNotificationSenderService {
	public static void main(String[] args) {
		SpringApplication.run(AdlyNotificationSenderService.class, args);
	}
}


