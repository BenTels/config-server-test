package nl.bentels.confserv.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@ComponentScan(basePackages = "nl.bentels.confserv.test")
@EnableRetry
public class ConfigServerTestBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerTestBookingServiceApplication.class, args);
	}

}
