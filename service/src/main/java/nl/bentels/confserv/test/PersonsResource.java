package nl.bentels.confserv.test;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.bentels.confserv.test.repository.Person;
import nl.bentels.confserv.test.repository.PersonRepository;

@RestController
public class PersonsResource {
	
	
	private static final Logger log = LoggerFactory.getLogger(PersonsResource.class);

	@Autowired
	private UUID serverId;
	
	@Autowired
	private PersonRepository personRepository;

	@GetMapping(path = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Person> getAllPersons() {
		
		log.info("Request handled by server " + serverId);
		
		return personRepository.findAll();
	}
	
}
