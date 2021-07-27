package nl.bentels.confserv.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
	
	public List<Person> findByLastName(String lastName);

	public List<Person> findByLastNameStartsWith(String lastNamePrefix);

}
