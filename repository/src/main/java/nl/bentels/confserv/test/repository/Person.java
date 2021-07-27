package nl.bentels.confserv.test.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "person")
@Data
public class Person {

	@Field(name = "_id")
	private String id;
	private String[] firstNames;
	private String[] middleNames;
	private String lastName;
	private String[] emailAddresses;
	private String dateOfBirth;
}
