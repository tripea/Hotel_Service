package com.tripeasy.hotel.hotelservice.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tripeasy.hotel.hotelservice.entity.Address;
import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.exception.InvalidInputException;
import com.tripeasy.hotel.hotelservice.service.HotelService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	private HashMap<Integer, String> review = new HashMap<>();
	private Set<Room> rooms = new HashSet<>();
	private List<String> pics = new ArrayList<String>();
	private List<String> hotelPic = new ArrayList<String>();

	private Map<String, List<String>> facilities = new HashMap<String, List<String>>();
	List<String> hotelFacilities = new ArrayList<String>();

	@Autowired
	private HotelService hotelService;

	@Test
	public void testGetHotelById() throws InvalidInputException {
		Hotel actualHotel = hotelService.getHotelById(102).get();
		Integer expected = 102;
		assertEquals(expected, actualHotel.getHotelId());

	}

	@Test
	public void testGetHotelByWrongId() {
		Optional<Hotel> actualHotel = hotelService.getHotelById(1111);
		assertFalse(actualHotel.isPresent());

	}

	public void testGetAllHotels() throws InvalidInputException {
		List<Hotel> actualHotel = hotelService.getAllHotel();
		assertFalse(actualHotel.isEmpty());

	}

	@Test
	public void testCreateHotelWithRightData() throws InvalidInputException {
		hotelFacilities.add("Wi-fi");
		hotelFacilities.add("Conference Fa0000cilities");
		hotelFacilities.add("Newspapers In Lobby");
		hotelFacilities.add("Room Service (24 Hours)");
		review.put(5, "Its Awesome");
		pics.add("room image");

		pics.add("room image2");
		pics.add("room image3");
		pics.add("room image4");
		pics.add("room image5");

		rooms.add(new Room(1, "AC", 4100.0, 2, "Double", true, pics));
		hotelPic.add("Hotel Image");
		hotelPic.add("Hotel Image2");
		hotelPic.add("Hotel Image3");
		hotelPic.add("Hotel Image4");
		facilities.put("Taj", hotelFacilities);

		Hotel newHotel = new Hotel(115, "Taj",
				new Address("Sector 2", "Neer to Cap", "Maharashtra", "India", "Pali", 415205), review, rooms, hotelPic,
				facilities, "In description it is best hotel in India", 50);
		hotelService.addNewHotel(newHotel);

	}

	@Test(expected = InvalidInputException.class)
	public void testCreateHotelWithWrongData() throws InvalidInputException {
		Hotel hotel = new Hotel();
		hotelService.addNewHotel(hotel);

	}

	@Test
	public void testGetHotelByCorrectCityName() {
		Optional<List<Hotel>> actualHotel = hotelService.getByCity("Mumbai");
		assertEquals(2, actualHotel.get().size());

	}

	@Test
	public void testGetHotelByInCorrectCityName() {
		Optional<List<Hotel>> actualHotel = hotelService.getByCity("moomba");
		System.out.println("in incorrectcity naem" + actualHotel.get());
		assertTrue(actualHotel.get().isEmpty());

	}

	@Test
	public void testUpdateWithRightHotel() throws InvalidInputException {
		hotelFacilities.add("Wi-fi");
		hotelFacilities.add("Conference Facilities");
		hotelFacilities.add("Newspapers In Lobby");
		hotelFacilities.add("Room Service (24 Hours)");
		review.put(5, "Its Awesome best hotel");
		pics.add("room image");

		pics.add("room image2");
		pics.add("room image3");
		pics.add("room image4");
		pics.add("room image5");

		rooms.add(new Room(1, "AC", 4100.0, 2, "Double", true, pics));
		hotelPic.add("Hotel Image");
		hotelPic.add("Hotel Image2");
		hotelPic.add("Hotel Image3");
		hotelPic.add("Hotel Image4");
		facilities.put("Taj", hotelFacilities);

		Hotel newHotel = new Hotel(555, "Taj",
				new Address("Sector 2", "Neer to Cap", "Maharashtra", "India", "Pali", 415205), review, rooms, hotelPic,
				facilities, "In description it is best hotel in India", 50);
		newHotel.setHotelName("Samrat Hotel");
		newHotel.setTotalAvailableRooms(50);
		Hotel updatedHotel = hotelService.update(newHotel);
		assertEquals("Samrat Hotel", updatedHotel.getHotelName());

	}

	@Test(expected = InvalidInputException.class)
	public void testUpdateWithWrongDataOfHotel() throws InvalidInputException {

		Hotel newHotel = new Hotel();
		Hotel updatedHotel = hotelService.update(newHotel);
		System.out.println("in update wrong data " + updatedHotel);
		assertEquals("Samrat Hotel", updatedHotel.getHotelName());

	}

	@Test
	@Ignore
	public void testDeleteHotelById() {
		hotelService.deleteHotelById(105);
		Optional<Hotel> hotel = hotelService.getHotelById(105);
		assertFalse(hotel.isPresent());

	}

	@Test
	public void testUpdateRoomWithBookingRoom() {
		Hotel hotel = hotelService.getHotelById(555).get();
		Hotel  actualHotel = hotelService.updateRoom(hotel, 4, true);
		Integer expected = 49;
		assertEquals(expected , actualHotel.getTotalAvailableRooms());

	}
	
	@Test
	public void testUpdateRoomWithNotBookingRoom() {
		Hotel hotel = hotelService.getHotelById(555).get();
		Hotel  actualHotel = hotelService.updateRoom(hotel, 4, false);
		Integer expected = 50;
		assertEquals(expected , actualHotel.getTotalAvailableRooms());

	}

}
