package com.travel.backpacker.repository.accommodation;

import com.travel.backpacker.model.accommodation.HotelImage;
import org.springframework.data.repository.Repository;

public interface HotelImageDao extends Repository<HotelImage, Long> {
    HotelImage save(HotelImage hotelImage);
}
