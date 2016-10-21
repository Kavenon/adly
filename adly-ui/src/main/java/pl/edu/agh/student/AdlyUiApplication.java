package pl.edu.agh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Sso
public class AdlyUiApplication extends WebSecurityConfigurerAdapter {

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

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/frontend/**");
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .logout().and()
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
	}


}
