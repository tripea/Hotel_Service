package com.tripeasy.hotel.hotelservice.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;
import com.tripeasy.hotel.hotelservice.exception.InvalidInputException;
import com.tripeasy.hotel.hotelservice.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public void addNewHotel(Hotel hotel) throws InvalidInputException {
		try {
			hotelRepository.save(hotel);
		} catch (Exception e) {
			throw new InvalidInputException("Invalid  input Data ");
		}

	}

	@Override
	public Hotel update(Hotel hotel) throws InvalidInputException {

		try {
			Hotel updatedHotel = hotelRepository.save(hotel);
			return updatedHotel;
		} catch (Exception e) {
			throw new InvalidInputException("Invalid  input Data ");
		}

	}

	@Override
	public Optional<Hotel> getHotelById(Integer hoteId) {

		return hotelRepository.findById(hoteId);

	}

	@Override
	public List<Hotel> getAllHotel() {

		return hotelRepository.findAll();

	}

	@Override
	public void deleteHotelById(Integer hotelId) {
		hotelRepository.deleteById(hotelId);
	}

	@Override
	public Optional<List<Hotel>> getByCity(String city) {
		return hotelRepository.findAllByAddressCity(city);
	}

	/*
	 * @Override public void updateRoom(Hotel hotel, Integer roomNumber, Integer
	 * numberOfGuest, Boolean statusAvailable) { Room room = null; Iterator iterator
	 * = hotel.getRooms().iterator(); Set<Room> setOfRooms = hotel.getRooms(); while
	 * (iterator.hasNext()) {
	 * 
	 * room = (Room) iterator.next(); if (room.getRoomNumber() == roomNumber) {
	 * room.setAvailable(statusAvailable); room.setNumberOfGuest(numberOfGuest);
	 * System.out.println("updated room object " + room);
	 * 
	 * setOfRooms.add(room); System.out.println("updated iterator object " +
	 * setOfRooms); }
	 * 
	 * } hotel.setRooms(setOfRooms); System.out.println("updated hotel  = " +
	 * hotel); hotelRepository.save(hotel); }
	 */

	@Override
	public Hotel updateRoom(Hotel hotel, Integer numberOfGuest, Boolean bookRoom) {
		Integer totalRooms = hotel.getTotalRooms();
		Integer totalAvailableRooms = hotel.getTotalAvailableRooms();
		System.out.println("total rooms = " + totalRooms);
		System.out.println("avalable rooms = " + totalAvailableRooms);
		if (bookRoom) {
			if (totalRooms >= totalAvailableRooms && totalAvailableRooms>0) {
				totalAvailableRooms--;
				hotel.setTotalAvailableRooms(totalAvailableRooms);
			}

		} else {
			if (totalAvailableRooms < totalRooms) {
				totalAvailableRooms++;
				hotel.setTotalAvailableRooms(totalAvailableRooms);
			}
		}
		System.out.println("updated hotel  = " + hotel);
		return hotelRepository.save(hotel);
	}
}
