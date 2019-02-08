package com.tripeasy.hotel.hotelservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tripeasy.hotel.hotelservice.entity.Address;
import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.repository.HotelRepository;
import com.tripeasy.hotel.hotelservice.service.HotelService;
import com.tripeasy.hotel.hotelservice.service.HotelServiceImpl;

@SpringBootApplication
public class HotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
		

	}
	
	private HashMap<Integer, String> review = new HashMap<>();
	private Set<Room> rooms = new HashSet<>();
	private List<String> pics = new ArrayList<String>();
	private List<String> hotelPic = new ArrayList<String>();

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
		pics.add("room image1");
		pics.add("room image2");
		pics.add("room image3");
		pics.add("room image4");
		pics.add("room image5");
		rooms.add(new Room(1, "AC", 4100.0, 2, "Double", true, pics));
		hotelPic.add("Hotel Image1");
		hotelPic.add("Hotel Image2");
		hotelPic.add("Hotel Image3");
		hotelPic.add("Hotel Image4");
		facilities.put("Taj", hotelFacilities);
		return (args) -> {
			repository.save(new Hotel(10, "TejHotel",
					new Address("Sector 2", "Neer to Cap", "Maharashtra", "India", "Mumbai", 415205), review, rooms,
					hotelPic, facilities, "In description it is best hotel in India", 50));

		};

	}

}

