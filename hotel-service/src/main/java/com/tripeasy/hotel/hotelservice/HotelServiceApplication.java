package com.tripeasy.hotel.hotelservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.HotelAddress;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.repository.HotelRepository;

@SpringBootApplication
public class HotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);

	}

	private HashMap<Integer, String> review = new HashMap<>();
	private Set<Room> rooms = new HashSet<>();
	private List<String> pics = new ArrayList<String>();
	private List<String> hotelPic = new ArrayList<String>();
	private List<String> hotelPic2 = new ArrayList<String>();
	
	private Map<String, List<String>> facilities = new HashMap<String, List<String>>();
	List<String> hotelFacilities = new ArrayList<String>();

	@Bean
	public RestTemplate restObject() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner populateData(HotelRepository repository) {

		hotelFacilities.add("Wi-fi");
		hotelFacilities.add("Conference Facilities");
		hotelFacilities.add("Newspapers In Lobby");
		hotelFacilities.add("Room Service (24 Hours)");
		review.put(5, "Its Awesome");
		pics.add("https://github.com/tripea/website-images/blob/master/Hotel_Images/taj%20hotel.jpg?raw=true");
		pics.add("room image2");
		pics.add("room image3");
		pics.add("room image4");
		pics.add("room image5");
		rooms.add(new Room(1, "AC", 4100.0, 2, "Double", true, pics));
		hotelPic.add("https://github.com/tripea/website-images/blob/master/Hotel_Images/taj%20hotel.jpg?raw=true");
		hotelPic.add("https://github.com/tripea/website-images/blob/master/Hotel_Images/holiday.jpg?raw=true");
		hotelPic.add("https://www.tajhotels.com/content/dam/thrp/destinations/Mumbai/16x7intro/Mumbai-Intro-4X3.jpg");
		hotelPic.add("https://megavenues.s3.amazonaws.com/assets/uploads/users/60/ads/The-Crystal-Room-at-Hotel-Sea-Princess.jpg");
		hotelPic2.add( "https://github.com/tripea/website-images/blob/master/Hotel_Images/holiday.jpg?raw=true");
		
		facilities.put("Taj", hotelFacilities);
		return (args) -> {
			repository.save(new Hotel(101, "TejHotel",
					new HotelAddress("Sector 2", "Neer to Cap", "Maharashtra", "India", "Mumbai", 415205), review,
					rooms, hotelPic, facilities, "In description it is best hotel in India", 50, 50));

			repository.save(new Hotel(102, "Hotel Maharaja",
					new HotelAddress("Sector 2", "Neer to Cap", "Maharashtra", "India", "Mumbai", 415205), review,
					rooms, hotelPic2, facilities, "In description it is best hotel in India", 60, 60));

			repository.save(new Hotel(103, "SP",
					new HotelAddress("Sector 2", "Neer to Cap", "Maharashtra", "India", "Mumbai", 415205), review,
					rooms, hotelPic, facilities, "In description it is best hotel in India", 40, 40));

			repository.save(new Hotel(104, "Assal Konkani",
					new HotelAddress("Sector 2", "Neer to Cap", "Maharashtra", "India", "Mumbai", 415205), review,
					rooms, hotelPic2, facilities, "In description it is best hotel in India", 45, 45));

			repository.save(new Hotel(105, "Taj",
					new HotelAddress("Sector 2", "Neer to Cap", "Maharashtra", "India", "Mumbai", 415205), review,
					rooms, hotelPic, facilities, "In description it is best hotel in India", 500, 500));

		};

	}

}
