package nl.bentels.confserv.test.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, ObjectId> {
	
	List<Booking> findByGuest(final String guestId);

}
