package com.travel.backpacker.models;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class UserLoginAction implements UserAction<HttpEntity>
{
	@Override
	public HttpEntity execute( UserAction userAction, Object... args )
	{
		return null;
	}
}
