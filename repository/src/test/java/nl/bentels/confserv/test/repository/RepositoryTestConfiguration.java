package nl.bentels.confserv.test.repository;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@TestConfiguration
public class RepositoryTestConfiguration {

	@Bean
	public MongoTemplate mongoTemplate(MongoClient client) {
		return new MongoTemplate(client, "test");
	}
	
	@Bean
	public MongoClient client() {
		MongoClientSettings settings = MongoClientSettings
				.builder()
				.applyConnectionString(new ConnectionString("mongodb+srv://bzt:bztPasswd@cluster-bzt-test-0.ew5ad.mongodb.net/test"))
				.build();
		return MongoClients.create(settings);
	}
}
