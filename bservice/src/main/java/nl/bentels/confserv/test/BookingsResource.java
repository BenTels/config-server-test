package nl.bentels.confserv.test;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.bentels.confserv.test.repository.Booking;
import nl.bentels.confserv.test.repository.BookingRepository;

@RestController
public class BookingsResource {

	@Autowired
	private BookingRepository repository;
	
	@GetMapping(path = "/bookings", params = {"guest-id" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> getBookingsByGuestId(@RequestParam("guest-id") final String guestId) {
		return repository.findByGuest(guestId);
	}
	
	@GetMapping(path = "/bookings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Booking> getBookingById(@PathVariable("id") final String id) {
		return repository.findById(new ObjectId(id));
	}
}
