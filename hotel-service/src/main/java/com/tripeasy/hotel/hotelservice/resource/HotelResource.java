package com.tripeasy.hotel.hotelservice.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.exception.InvalidInputException;
import com.tripeasy.hotel.hotelservice.service.HotelService;

@RequestMapping("/hotels")
@RestController
public class HotelResource {

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) throws InvalidInputException {
		if (hotel.getHotelId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		hotelService.addNewHotel(hotel);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotelsOfCity(@RequestParam("city") String city) {
		List<Hotel> hotels = hotelService.getByCity(city).get();
		if (hotels.isEmpty()) {
			return new ResponseEntity<>(hotels, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getAllHotel();
		if (hotels.isEmpty()) {
			return new ResponseEntity<>(hotels, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable Integer hotelId) {
		Optional<Hotel> hotel = hotelService.getHotelById(hotelId);
		System.out.println("Outside if " + hotel);
		if (!hotel.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
	}

	@PutMapping      
	public void updateHotel(@RequestBody Hotel hotel) throws InvalidInputException {
 			hotelService.update(hotel);
		 
	}

	@PutMapping("/{hotelId}")
	public void updateAvailabilityOfRoom(@PathVariable Integer hotelId, @RequestParam Integer numberOfGuest,
			@RequestParam Boolean bookRoom) {
		System.out.println("para are  = " +hotelId +"  " +numberOfGuest + "guest " +bookRoom);
		Hotel hotel = hotelService.getHotelById(hotelId).get();
		System.out.println("hotel is =" +hotel);
		hotelService.updateRoom(hotel, numberOfGuest, bookRoom);
	}

	@DeleteMapping("/{hotelId}")
	public void deleteHotelById(@PathVariable Integer hotelId) {
		/*
		 * Optional<Hotel> hotel = hotelService.getHotelById(hotelId); if
		 * (!hotel.isPresent()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
		 */
		hotelService.deleteHotelById(hotelId);
	}
}
