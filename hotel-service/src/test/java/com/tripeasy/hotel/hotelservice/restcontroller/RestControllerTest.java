package com.tripeasy.hotel.hotelservice.restcontroller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tripeasy.hotel.hotelservice.entity.Address;
import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.resource.HotelResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

	private HashMap<Integer, String> review = new HashMap<>();
	private Set<Room> rooms = new HashSet<>();
	private List<String> pics = new ArrayList<String>();
	private List<String> hotelPic = new ArrayList<String>();

	private Map<String, List<String>> facilities = new HashMap<String, List<String>>();
	List<String> hotelFacilities = new ArrayList<String>();

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testOfWrongURL() throws Exception {

		ResponseEntity<HotelResource> responseEntity = testRestTemplate.getForEntity("/hotel/", HotelResource.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	}

	@Test
	public void testOfBadRequest() throws Exception {

		ResponseEntity<HotelResource> responseEntity = testRestTemplate.getForEntity("/hotels/tejas",
				HotelResource.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	}

	@Test
	public void testOfGetAllHotels() throws Exception {

		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels", String.class);
		System.out.println(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testOfGetHotelsByCityNameExistsInDatabse() throws Exception {

		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels/?city=Mumbai", String.class);
		System.out.println(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testOfGetHotelsByCityNameDoesntExist() throws Exception {

		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels/?city=Pune", String.class);
		System.out.println(responseEntity);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void testOfGetHotelsByHotelIdExist() throws Exception {

		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels/102", String.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testOfGetHotelsByHotelIdDosentExist() throws Exception {

		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels/1111", String.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	@Ignore
	public void testOfdeleteHotelByIdExistInDb() throws Exception {

		testRestTemplate.delete("/hotels/105");
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels/105", String.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void testOfAddNewHotelWithRightdata() throws Exception {

		hotelFacilities.add("Wi-fi");
		hotelFacilities.add("Conference Facilities");
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

		Hotel newHotel = new Hotel(105, "Taj",
				new Address("Sector 2", "Neer to Cap", "Maharashtra", "India", "Pali", 415205), review, rooms, hotelPic,
				facilities, "In description it is best hotel in India", 50);
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/hotels/", newHotel, String.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	public void testOfAddNewHotelWithWrongData() throws Exception {

		Hotel newHotel = new Hotel();
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/hotels/", newHotel, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void testOfUpdateHotelWithRightdata() throws Exception {

		hotelFacilities.add("Wi-fi available");
		hotelFacilities.add("Conference Facilities");
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

		Hotel newHotel = new Hotel(110, "Taj",
				new Address("Sector 2", "Neer to Cap", "Maharashtra", "India", "Pali", 415205), review, rooms, hotelPic,
				facilities, "In description it is best hotel in India", 50);

		testRestTemplate.put("/hotels/110", newHotel);

		ResponseEntity<Hotel> actual = testRestTemplate.getForEntity("/hotels/110", Hotel.class);
		System.out.println("in update right data " + actual.getBody().getHotelName());
		assertEquals("Taj", newHotel.getHotelName());
	}

}
