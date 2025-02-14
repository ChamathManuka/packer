package com.travel.backpacker.service.interceptors;

import com.travel.backpacker.dto.UserWrapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressInterceptor implements HandlerInterceptor
{
	//do things before coming to the controller
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{
		System.out.println(request.getRequestURI());
		UserWrapper wrapper = getUserWrapper( request );
		wrapper.setRemoteAddress( request.getRemoteAddr() );
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	UserWrapper getUserWrapper(HttpServletRequest httpServletRequest )
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
