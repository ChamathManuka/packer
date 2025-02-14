package com.travel.backpacker.repository.accommodation;

import com.travel.backpacker.model.accommodation.RoomType;
import org.springframework.data.repository.Repository;

public interface RoomTypeDao extends Repository<RoomType, Integer> {
    RoomType save(RoomType RoomType);

    RoomType findRoomTypeByRoomTypeId(Long roomTypeId);
}
