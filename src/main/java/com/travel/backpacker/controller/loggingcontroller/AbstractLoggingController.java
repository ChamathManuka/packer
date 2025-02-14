package com.travel.backpacker.controller.loggingcontroller;

import com.travel.backpacker.controller.Controller;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.service.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

public abstract class AbstractLoggingController<U extends User> implements Controller<U>
{
	private final Controller controller;
	Logger logger = LoggerFactory.getLogger( AbstractLoggingController.class );
	protected AbstractLoggingController( Controller controller )
	{
		this.controller = controller;
	}

	@Override
	public HttpEntity execute( UserWrapper userWrapper, Operation<U> operation )
	{
		logger.info( "Now operating : " + operation.getClass() );
		return controller.execute( userWrapper, operation );
	}
}
