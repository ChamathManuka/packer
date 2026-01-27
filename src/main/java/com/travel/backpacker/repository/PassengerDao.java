package com.travel.backpacker.repository;

import com.travel.backpacker.model.Passenger;
import org.springframework.data.repository.Repository;

public interface PassengerDao extends Repository<Passenger, Long>, UserDao<Passenger> {
}
