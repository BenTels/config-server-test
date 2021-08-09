package nl.bentels.confserv.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nl.bentels.confserv.test.repository.Booking.RoomType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RepositoryConfiguration.class, RepositoryTestConfiguration.class})
public class BookingRepositoryTest {

	@Autowired
	private BookingRepository objectUnderTest;
	@Autowired
	private PersonRepository personRepo;
	
	@Test
	public void repositoryIsInjected() {
		assertNotNull(objectUnderTest);
	}
	
	@Test
	@Disabled
	public void whenBookingInserted_thenOkay() {
		Booking booking = new Booking();
		booking.setArrivalDate(LocalDate.now().plusDays(1));
		booking.setDepartureDate(LocalDate.now().plusDays(4));
		booking.setRoomType(RoomType.DBL);
		booking.setGuest("43185022-f9bf-4dfe-ad59-4f88130fb2fc");
		
		objectUnderTest.save(booking);
	}
	
	@Test
	public void whenBookingRetrieved_thenOkay() {
		LocalDate arr = LocalDate.of(2021, Month.AUGUST.getValue(), 8);
		LocalDate dep = LocalDate.of(2021, Month.AUGUST.getValue(), 11);
		final String guestId = "43185022-f9bf-4dfe-ad59-4f88130fb2fc";
		Booking expected = new Booking("610f018d7a1d7117b5c22531", arr, dep, RoomType.DBL, guestId);
		
		Booking actual = objectUnderTest.findById(new ObjectId("610f018d7a1d7117b5c22531")).get();
		
		assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	public void generateTestData() {
		List<String> pids = StreamSupport.stream(personRepo.findAll().spliterator(), false)
		.map(pers -> pers.getId())
		.toList();
		
		List<Booking> bookings = IntStream.range(0, 2000)
		.mapToObj(n -> {
			
			Random r = ThreadLocalRandom.current();
			LocalDate arr = LocalDate.now().plusDays(r.nextInt(365));
			LocalDate dep = arr.plusDays(r.nextInt(15));
			RoomType rt = RoomType.values()[r.nextInt(RoomType.values().length)];
			
			Booking booking = new Booking();
			booking.setArrivalDate(arr);
			booking.setDepartureDate(dep);
			booking.setRoomType(rt);
			booking.setGuest(pids.get(r.nextInt(pids.size())));
			
			return booking;
		})
		.toList();
		
		objectUnderTest.saveAll(bookings);
	}
}
