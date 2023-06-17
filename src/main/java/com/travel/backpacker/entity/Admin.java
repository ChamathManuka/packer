package com.travel.backpacker.entity;

import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserAction;
import org.springframework.http.HttpEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "admin")
public class Admin implements User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String username;

	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false, unique = true)
	private char[] password;

	@Column(name = "active", nullable = false)
	private boolean active;

	public Admin()
	{
	}

	public Admin( String username, String email, char[] password, boolean active )
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public char[] getPassword()
	{
		return password;
	}

	public String getEmail()
	{
		return email;
	}

	public String getUsername()
	{
		return username;
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive( boolean active )
	{
		this.active = active;
	}

	@Override
	public UserType getUserType()
	{
		return UserType.ADMIN;
	}

	@Override
	public HttpEntity execute( UserAction userAction, Object... args )
	{
		return null;
	}
}
