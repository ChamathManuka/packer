package com.travel.backpacker.repository;

import com.travel.backpacker.dto.User;

import java.util.Optional;

public interface UserDao<T extends User>
{
	//using optional objects in jdk8 to deal with null pointer execptions
	Optional<T> findById( long id );

	Optional<T> findByUsername( String name );

	T save( T t );
}
