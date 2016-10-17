package pl.edu.agh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AdlyEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdlyEurekaServiceApplication.class, args);
	}
}
