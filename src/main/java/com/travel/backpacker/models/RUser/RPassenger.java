package com.travel.backpacker.models.RUser;

import com.travel.backpacker.models.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RPassenger implements RUser
{
	@Min(value = 0, message = "Id should not be provided")
	private long id;

	private String username;
	private String firstname;
	private String lastname;

	@NotNull(message = "Password should not be empty")
	private char[] password;

	@NotNull(message = "Email should not be empty")
	private String email;

	@NotNull(message = "Phone should not be empty")
	private String phone;

	private User.Gender gender;

	private String platform;

	private RAddress rAddress;

	public RPassenger()
	{
	}

	public RPassenger( long id, String username, String firstname, String lastname, @NotNull(message = "Password should not be empty") char[] password, String email, String phone, User.Gender gender, String platform, RAddress rAddress )
	{
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.platform = platform;
		this.rAddress = rAddress;
	}

	public long getId()
	{
		return id;
	}

	public void setId( long id )
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname( String firstname )
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname( String lastname )
	{
		this.lastname = lastname;
	}

	public char[] getPassword()
	{
		return password;
	}

	public void setPassword( char[] password )
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	public User.Gender getGender()
	{
		return gender;
	}

	public void setGender( User.Gender gender )
	{
		this.gender = gender;
	}

	public String getPlatform()
	{
		return platform;
	}

	public void setPlatform( String platform )
	{
		this.platform = platform;
	}

	public RAddress getrAddress()
	{
		if ( this.rAddress == null )
		{
			RAddress defaultAddress = new RAddress();
			defaultAddress.setLine1( "Not Provided" );
			defaultAddress.setCity( "Not Provided" );
			defaultAddress.setPostcode( "Not Provided" );
			setrAddress( defaultAddress );
		}
		return this.rAddress;
	}

	public void setrAddress( RAddress rAddress )
	{
		this.rAddress = rAddress;
	}
}
