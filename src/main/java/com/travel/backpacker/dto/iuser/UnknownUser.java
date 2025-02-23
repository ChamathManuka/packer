package com.travel.backpacker.dto.iuser;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserAction;
import org.springframework.http.HttpEntity;

import java.time.LocalDateTime;

public class UnknownUser implements User
{
	@Override
	public Long getId()
	{
		return null;
	}

	@Override
	public void setId(Long id) {

	}

	@Override
	public UserType getUserType()
	{
		return UserType.UNKNOWNUSER;
	}

	@Override
	public void setUserType(UserType userType) {

	}

	@Override
	public String getUsername()
	{
		return null;
	}

	@Override
	public void setUsername(String username) {

	}

	@Override
	public String getOtpCode() {
		return "";
	}

	@Override
	public void setOtpCode(String otpCode) {

	}

	@Override
	public void setOptExpiry(LocalDateTime optExpiry) {

	}

	@Override
	public LocalDateTime getOptExpiry() {
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
	public HttpEntity execute(UserAction userAction, Object... args )
	{
		return null;
	}
}
