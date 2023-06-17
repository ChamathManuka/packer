package com.travel.backpacker.controllers.implcontroller;

import com.travel.backpacker.controllers.Controller;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

public abstract class AbstractImplementationController<U extends User> implements Controller<U>
{
	Logger logger = LoggerFactory.getLogger( this.getClass() );
	@Override
	public HttpEntity execute( UserWrapper userWrapper, Operation<U> operation )
	{
		logger.info( "Now operating : " + operation.getClass() );
		return operation.execute( ( U ) new UnknownUser() );
	}
}
