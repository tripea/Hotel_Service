package com.tripeasy.hotel.hotelservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.exception.InvalidInputException;

public interface HotelService {

	void addNewHotel(Hotel newHotel) throws InvalidInputException;

	Hotel update(Hotel hotel) throws InvalidInputException;

	Optional<Hotel> getHotelById(Integer hoteId);

	void deleteHotelById(Integer hotelId);

	Optional<List<Hotel>> getByCity(String city);

	List<Hotel> getAllHotel();

	 Hotel updateRoom(Hotel hotel, Integer numberOfGuest, Boolean bookRoom);

	//void updateRoom(Hotel hotel, Integer roomNumber, Integer numberOfGuest, Boolean statusAvailable);

 
	//void updateRoom(Hotel hotel, Integer roomNumber, Integer numberOfGuest, Boolean statusAvailable);

}
