package nl.bentels.confserv.test.repository;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "booking")
public class Booking {

	public static enum RoomType {
		SGL, DBL, TWN, TRP, JST, SUI
	}
	
	@Id
	private String identifier;
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private RoomType roomType;
	private String guest;
}
