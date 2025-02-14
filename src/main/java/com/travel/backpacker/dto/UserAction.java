package com.travel.backpacker.dto;

public interface UserAction<T>
{
	T execute( UserAction userAction, Object... args );
}
