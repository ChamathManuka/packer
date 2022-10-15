package com.travel.backpacker.entity;

import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserAction;
import org.springframework.http.HttpEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "Operator")
public class Operator implements User
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

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	@Override
	public UserType getUserType()
	{
		return UserType.operator;
	}

	@Override
	public String getUsername()
	{
		return this.username;
	}

	@Override
	public char[] getPassword()
	{
		return this.password;
	}

	@Override
	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public HttpEntity execute( UserAction userAction, Object... args )
	{
		return null;
	}
}
