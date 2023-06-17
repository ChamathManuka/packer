package com.travel.backpacker.controllers.endpointcontroller.access;

import com.travel.backpacker.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginData
{
	@NotNull(message = "username is mandatory")
	private String username;

	@Pattern(regexp = "[a-zA-Z0-9]{6}")
	private char[] password;

	@NotNull(message = "type is mandatory")
	private User.UserType type;

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public char[] getPassword()
	{
		return password;
	}

	public void setPassword( char[] password )
	{
		this.password = password;
	}

	public User.UserType getType()
	{
		return type;
	}

	public void setType( User.UserType type )
	{
		this.type = type;
	}
}
