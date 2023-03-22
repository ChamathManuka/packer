package com.travel.backpacker.controllers.lockcontroller;

import com.travel.backpacker.controllers.Controller;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

public abstract class AbstractLockController<U extends User> implements Controller<U>
{
	private final Controller controller;
	Logger logger = LoggerFactory.getLogger( this.getClass() );
	protected AbstractLockController( Controller controller )
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

