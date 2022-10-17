package com.travel.backpacker.interceptors;

import com.sun.jdi.request.InvalidRequestStateException;
import com.travel.backpacker.Properties.DefaultPackerProperties;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractJwtInterceptor implements HandlerInterceptor
{
	private final DefaultPackerProperties packerProperties;

	protected AbstractJwtInterceptor( DefaultPackerProperties packerProperties )
	{
		this.packerProperties = packerProperties;
	}

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

	public Map<String, UserWrapper> createPackerPayload( UserWrapper userWrapper )
	{
		Map<String, UserWrapper> payload = new HashMap<>();
		payload.put( "payload", userWrapper );
		return payload;
	}

	public UserWrapper updateWrapper( User user )
	{
		UserWrapper userWrapper = new UserWrapper.Builder().buildWrapper();
		userWrapper.getRoles().add( user.getUserType() );
		switch ( user.getUserType() )
		{
			case ADMIN:
				userWrapper.setAdminId( user.getId() );
				break;
			case PASSENGER:
				userWrapper.setPassengerId( user.getId() );
				break;
			case OPERATOR:
				userWrapper.setOperatorId( user.getId() );
				break;
			case SUPPLIER:
				userWrapper.setSupplierId( user.getId() );
				break;
			case UNKNOWNUSER:
				userWrapper.setUnknownUserId( user.getId() );
				break;
		}
		userWrapper.setActive( user.isActive() );
		return userWrapper;
	}

	public String getPackerToken( User user )
	{
		try
		{
			UserWrapper wrapper = this.updateWrapper( user );

			String subject = user.getUserType().name() + "-" + user.getId();
			java.util.Date now = Date.from( ZonedDateTime.now().toInstant() );
			java.util.Date expiryDate = Date.from( ZonedDateTime.now().plus( 1, ChronoUnit.HOURS ).toInstant() );

			byte[] key = packerProperties.getKey().getBytes( StandardCharsets.UTF_8 );
			JwtBuilder jwtBuilder = Jwts.builder().setExpiration( expiryDate ).setSubject( subject ).setIssuedAt( now ).signWith( SignatureAlgorithm.HS256, key );
			Map<String, UserWrapper> payload = createPackerPayload( wrapper );
			payload.forEach( jwtBuilder::claim );
			return jwtBuilder.compact();

		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}
}
