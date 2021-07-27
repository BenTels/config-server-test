package nl.bentels.confserv.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.bentels.confserv.test.repository.Person;
import nl.bentels.confserv.test.repository.PersonRepository;

@RestController
public class PersonsResource {
	
	@Autowired
	private PersonRepository personRepository;

	@GetMapping(path = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
}
