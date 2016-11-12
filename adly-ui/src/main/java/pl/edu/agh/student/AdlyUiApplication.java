package pl.edu.agh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AdlyUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdlyUiApplication.class, args);
	}

	@Configuration
	protected static class WebConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addViewControllers( ViewControllerRegistry registry ) {
			registry.addViewController( "/" ).setViewName( "index" );
			registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
			super.addViewControllers( registry );
		}
	}

}
