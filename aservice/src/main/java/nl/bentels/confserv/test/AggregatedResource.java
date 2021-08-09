package nl.bentels.confserv.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class AggregatedResource {
	
	
	private static final Logger log = LoggerFactory.getLogger(AggregatedResource.class);

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(true);
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Hello World!!");
	}
	
	
	@GetMapping(path = "/full/{namePrefix}")
	public ResponseEntity<JsonNode> getFullDetailsByNamePrefix(@PathVariable("namePrefix") final String prefix) throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = restTemplate.getForEntity("http://TEST-APP:8080/persons/?filter=" + prefix , String.class);
		String body = response.getBody();
		
		JsonNode fullDetails = getAllDetails(body);
		
		
		return ResponseEntity.ok(fullDetails);
	}

	private JsonNode getAllDetails(String body) throws JsonMappingException, JsonProcessingException {
		JsonNode root = mapper.readTree(body);
		root.elements().forEachRemaining(this::addBookings);
		return root;
	}
	
	private void addBookings(JsonNode personNode) {
		String guestId = personNode.get("id").textValue();
		ResponseEntity<String> response = restTemplate.getForEntity("http://BOOKING-APP:8081/bookings?guest-id=" + guestId , String.class);
		if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
			try {
				JsonNode bookings = mapper.readTree(response.getBody());
				
				((ObjectNode)personNode).set("bookings", bookings);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
