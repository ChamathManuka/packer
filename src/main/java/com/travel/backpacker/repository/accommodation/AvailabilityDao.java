package com.travel.backpacker.repository.accommodation;

import com.travel.backpacker.model.accommodation.Availability;
import org.springframework.data.repository.Repository;

public interface AvailabilityDao extends Repository<Availability, Integer> {
    Availability findById(int id);

    Availability save(Availability availability);
}
