package pl.edu.agh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class AdlyConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdlyConfigServerApplication.class, args);
	}

}
