package com.travel.backpacker.model;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserAction;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(char[] password) {
		this.password = password;
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
	public void setUserType(UserType userType) {

	}

	@Override
	public HttpEntity execute( UserAction userAction, Object... args )
	{
		return null;
	}
}
