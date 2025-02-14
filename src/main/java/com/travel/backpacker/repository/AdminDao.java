package com.travel.backpacker.repository;

import com.travel.backpacker.model.Admin;
import org.springframework.data.repository.Repository;

public interface AdminDao extends Repository<Admin, Long>, UserDao<Admin>
{

}
