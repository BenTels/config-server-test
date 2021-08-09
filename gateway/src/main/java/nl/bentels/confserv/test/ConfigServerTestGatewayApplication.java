package nl.bentels.confserv.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConfigServerTestGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerTestGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path route 0", p -> p.path("/persons").uri("lb://TEST-APP"))
				.route("path route 1", p -> p.path("/bookings/**").uri("lb://BOOKING-APP"))
				.route("path route 2", p -> p.path("/full/**").uri("lb://AGGREGATION-APP"))
				.route("path route 2", p -> p.path("/test").uri("lb://AGGREGATION-APP"))
				.build();
	}
}
