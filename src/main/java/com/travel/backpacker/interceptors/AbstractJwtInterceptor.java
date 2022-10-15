package com.travel.backpacker.interceptors;

import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractJwtInterceptor implements HandlerInterceptor
{
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{
		return HandlerInterceptor.super.preHandle( request, response, handler );
	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception
	{
		HandlerInterceptor.super.postHandle( request, response, handler, modelAndView );
	}

	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception
	{
		HandlerInterceptor.super.afterCompletion( request, response, handler, ex );
	}

	private void filterRequest( ServletRequest request, ServletResponse response )
	{
		HttpServletRequest httpServletRequest = ( HttpServletRequest ) request;
		String prefix = "Bearer";
		String authHandler = httpServletRequest.getHeader( "authorization" );
		if ( authHandler == null || !authHandler.startsWith( prefix ) )
		{
			throw new InvalidRequestStateException();
		}

	}
}
