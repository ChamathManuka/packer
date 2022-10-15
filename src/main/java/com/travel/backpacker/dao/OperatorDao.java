package com.travel.backpacker.dao;

import com.travel.backpacker.entity.Operator;
import org.springframework.data.repository.Repository;

public interface OperatorDao extends Repository<Operator, Long>, UserDao<Operator>
{
}
