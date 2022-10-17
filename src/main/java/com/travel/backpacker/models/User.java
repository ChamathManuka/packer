package com.travel.backpacker.models;

import org.springframework.http.HttpEntity;

import java.io.Serializable;

public interface User extends UserAction<HttpEntity>, Serializable
{
	Long getId();

	public UserType getUserType();

	String getUsername();

	public char[] getPassword();

	public boolean isActive();

	public enum UserType
	{
		UNKNOWNUSER, OPERATOR, PASSENGER, ADMIN, SUPPLIER;
	}

	public enum Gender
	{
		male, female, notprovided;
	}

}
