package com.tripeasy.hotel.hotelservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tripeasy.hotel.hotelservice.entity.Hotel;
import com.tripeasy.hotel.hotelservice.entity.Room;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,Integer>{

	Optional<List<Hotel>> findAllByAddressCity(String city);

	
}
