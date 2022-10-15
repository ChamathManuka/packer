package com.travel.backpacker.models;

public interface UserAction<T>
{
	T execute( UserAction userAction, Object... args );
}
