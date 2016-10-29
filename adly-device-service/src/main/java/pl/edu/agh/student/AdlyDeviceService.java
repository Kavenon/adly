package pl.edu.agh.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableBinding(BeaconChannels.class)
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableResourceServer
@EnableOAuth2Client
public class AdlyDeviceService {

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(
			OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}

	public static void main(String[] args) {
		SpringApplication.run(AdlyDeviceService.class, args);
	}


}

interface BeaconChannels {

	@Input
	SubscribableChannel input();
}

@MessageEndpoint
class BeaconDiscoverProcessor {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@ServiceActivator(inputChannel = "input")
	public void onBeaconDiscover(BeaconDiscoverEvent test){
		LOG.info("Received" + test);
	}
}
