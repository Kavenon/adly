package pl.edu.agh.student;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.hateoas.Resources;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableResourceServer
@EnableHystrix
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@EnableFeignClients
public class AdlyEdgeApplication  {


//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
    public static void main(String[] args) {
		SpringApplication.run(AdlyEdgeApplication.class, args);
	}
}

@FeignClient("adly-reservation-service")
interface UserClientV1 {
	@RequestMapping(method = RequestMethod.GET, value = "/reservations")
	Resources<Reservations> read();
}

//@FeignClient("adly-auth")
//interface AuthClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/user")
//    Resource<String> read();
//}
//
@RestController
@RequestMapping("/reservations")
class ReservationApiGatewayRestController {

    private final UserClientV1 userClientV1;

    @Autowired
    public ReservationApiGatewayRestController(UserClientV1 userClientV1) {
        this.userClientV1 = userClientV1;
    }

    public Collection<String> fallback(){
        return new ArrayList();
    }

    @RequestMapping("/names")
    @HystrixCommand(fallbackMethod = "fallback")
    public Collection<String> names(){

        return userClientV1.read().getContent().stream().map(Reservations::getReservationName).collect(Collectors.toList());
    }
}
//
//@RestController
//class MeController {
//
//    private final AuthClient authClient;
//
//    @Autowired
//    public MeController( AuthClient authClient) {
//        this.authClient = authClient;
//    }
//
//    public String fallback(){
//        return "empty";
//    }
//
//    @RequestMapping("/me")
//    @HystrixCommand(fallbackMethod = "fallback")
//    public String names(){
//        return authClient.read().getContent();
//    }
//}
class Reservations {
	private String reservationName;

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
}