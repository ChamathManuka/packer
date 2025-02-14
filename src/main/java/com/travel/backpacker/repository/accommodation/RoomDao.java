package com.travel.backpacker.repository.accommodation;

import com.travel.backpacker.model.accommodation.Room;
import org.springframework.data.repository.Repository;

public interface RoomDao extends Repository<Room, Integer> {
    Room save(Room Room);

    Room findRoomByRoomId(Long roomId);
}
