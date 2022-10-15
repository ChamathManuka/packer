package com.travel.backpacker.interceptors;

import com.travel.backpacker.models.UserWrapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressInterceptor implements HandlerInterceptor
{
	//do things before coming to the controller
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{
		UserWrapper wrapper = getUserWrapper( request );
		wrapper.setRemoteAddress( request.getRemoteAddr() );
		return true;
	}

	UserWrapper getUserWrapper( HttpServletRequest httpServletRequest )
	{
		UserWrapper wrapper = ( UserWrapper ) httpServletRequest.getAttribute( "userWrapper" );
		if ( wrapper == null )
		{
			wrapper = new UserWrapper.Builder().buildWrapper();
			httpServletRequest.setAttribute( "userWrapper", wrapper );
		}
		return wrapper;
	}
}
