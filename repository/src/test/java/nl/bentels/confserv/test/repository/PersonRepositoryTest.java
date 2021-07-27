package nl.bentels.confserv.test.repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import org.assertj.core.util.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RepositoryConfiguration.class, RepositoryTestConfiguration.class})
class PersonRepositoryTest {

	@Autowired
	private PersonRepository objectUnderTest;
	
	@Test
	void repositoryProxyIsInjected() {
		assertTrue(Objects.nonNull(objectUnderTest));
	}
	
	@Test
	public void whenFullCollectionQuery_thenAllDocumentsReturned() {
		long count = objectUnderTest.count();
		
		Iterable<Person> findAll = objectUnderTest.findAll();
		assertEquals(count, Streams.stream(findAll).count());
	}

	@Test
	public void whenLookingForTels_thenFindTwo() {
		List<Person> findByLastName = objectUnderTest.findByLastName("Tels");
		assertEquals(2, findByLastName.size());		
	}
	
	@Test
	public void whenLookingForOba_thenFindObama() {
		List<Person> oba = objectUnderTest.findByLastNameStartsWith("Oba");
		assertEquals(1, oba.size());		
		assertEquals("Obama", oba.get(0).getLastName());		
		assertArrayEquals(new String[] {"Barack"}, oba.get(0).getFirstNames());		
		assertArrayEquals(new String[] {"Hussein"}, oba.get(0).getMiddleNames());		
	}
	
}
