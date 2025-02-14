package com.travel.backpacker.repository;

import com.travel.backpacker.model.Operator;
import org.springframework.data.repository.Repository;

public interface OperatorDao extends Repository<Operator, Long>, UserDao<Operator>
{
}
