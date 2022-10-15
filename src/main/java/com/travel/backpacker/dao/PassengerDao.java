package com.travel.backpacker.dao;

import com.travel.backpacker.entity.Passenger;
import org.springframework.data.repository.Repository;

public interface PassengerDao extends Repository<Passenger, Long>, UserDao<Passenger>
{
}
