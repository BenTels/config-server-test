package nl.bentels.confserv.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.RetryOperations;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@SpringBootApplication
@ComponentScan(basePackages = "nl.bentels.confserv.test")
@EnableRetry
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class);
	}
	
//	@Bean(name = "configServerRetryInterceptor")
//	public RetryOperationsInterceptor configServerRetryInterceptor() {
//		return RetryInterceptorBuilder
//		.stateless()
//		.backOffOptions(1000, 2, 1_000_000)
//		.maxAttempts(200)
//		.build();
//	}
	
}
