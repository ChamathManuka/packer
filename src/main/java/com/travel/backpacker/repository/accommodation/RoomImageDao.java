package com.travel.backpacker.repository.accommodation;

import com.travel.backpacker.model.accommodation.RoomImage;
import org.springframework.data.repository.Repository;

public interface RoomImageDao extends Repository<RoomImage, Integer> {
    RoomImage save(RoomImage RoomImage);
}
