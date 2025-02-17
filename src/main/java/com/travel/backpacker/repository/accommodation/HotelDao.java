package com.travel.backpacker.repository.accommodation;


import com.travel.backpacker.model.accommodation.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface HotelDao extends JpaRepository<Hotel, Long>
{
	Hotel save(Hotel hotel);

	Hotel findHotelByHotelId(Long hotelId);

	List<Hotel> getAllByCity(String city);
}
