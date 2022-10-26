package com.travel.backpacker.controllers.implcontroller;

import com.travel.backpacker.controllers.Controller;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import org.springframework.http.HttpEntity;

public abstract class AbstractImplementationController<U extends User> implements Controller<U>
{
	@Override
	public HttpEntity execute( UserWrapper userWrapper, Operation<U> operation )
	{
		return operation.execute( ( U ) new UnknownUser() );
	}
}
