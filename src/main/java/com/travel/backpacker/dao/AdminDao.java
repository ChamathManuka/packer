package com.travel.backpacker.dao;

import com.travel.backpacker.entity.Admin;
import org.springframework.data.repository.Repository;

public interface AdminDao extends Repository<Admin, Long>, UserDao<Admin>
{

}
