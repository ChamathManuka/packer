package com.travel.backpacker.models;

import org.springframework.http.HttpEntity;

import java.io.Serializable;

public interface User extends UserAction<HttpEntity>, Serializable
{

	public UserType getUserType();

	String getUsername();

	public char[] getPassword();

	public boolean isActive();

	public enum UserType
	{
		unknownUser, operator, passenger, admin, supplier;
	}

	public enum Gender
	{
		male, female, notprovided;
	}

}
