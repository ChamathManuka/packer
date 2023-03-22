package com.travel.backpacker.dao.schedulers.content;

import com.travel.backpacker.entity.schedulers.content.Hotel;
import org.springframework.data.repository.Repository;

public interface HotelDao extends Repository<Hotel, Long>
{
	Hotel save(Hotel hotel);
}
