package pl.edu.agh.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class AdlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdlyApplication.class, args);
	}
}

@RestController
@RefreshScope
class MessageController {

    private final String message;

    @Autowired
    public MessageController(@Value("${message}") String message) {
        this.message = message;
    }

    @RequestMapping(method = RequestMethod.GET,value="/msg")
    String read(){
        return this.message;
    }
}
@Component
class SimpleCLR implements CommandLineRunner {


    private ReservationRepository reservationRepository;

    @Autowired
    public SimpleCLR(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("test", "test2", "test3")
                .forEach(s -> reservationRepository.save(new Reservation(s)));

        reservationRepository.findAll().forEach(System.out::println);
    }
}
@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
