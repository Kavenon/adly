package pl.edu.agh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import pl.edu.agh.student.services.NotificationChannel;
import pl.edu.agh.student.services.catchers.BeaconChannels;

@IntegrationComponentScan
@EnableBinding({BeaconChannels.class, NotificationChannel.class})
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableResourceServer
@EnableOAuth2Client
public class AdlyRuleService {

	@Bean
	@LoadBalanced
	public OAuth2RestTemplate oAuth2RestTemplate(
			OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}

	public static void main(String[] args) {
		SpringApplication.run(AdlyRuleService.class, args);
	}


}




