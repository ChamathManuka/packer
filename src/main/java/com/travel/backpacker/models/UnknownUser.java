package com.travel.backpacker.models;

import org.springframework.http.HttpEntity;

public class UnknownUser implements User
{
	@Override
	public UserType getUserType()
	{
		return UserType.unknownUser;
	}

	@Override
	public String getUsername()
	{
		return null;
	}

	@Override
	public char[] getPassword()
	{
		return new char[0];
	}

	@Override
	public boolean isActive()
	{
		return false;
	}

	@Override
	public HttpEntity execute( UserAction userAction, Object... args )
	{
		return null;
	}
}
