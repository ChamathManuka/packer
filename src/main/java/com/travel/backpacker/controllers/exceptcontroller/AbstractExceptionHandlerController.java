package com.travel.backpacker.controllers.exceptcontroller;

import com.travel.backpacker.controllers.Controller;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import org.springframework.http.HttpEntity;

public abstract class AbstractExceptionHandlerController<U extends User> implements Controller<U>
{
	private final Controller controller;

	public AbstractExceptionHandlerController( Controller controller )
	{
		this.controller = controller;
	}

	@Override
	public HttpEntity execute( UserWrapper userWrapper, Operation<U> operation )
	{
		return controller.execute( userWrapper, operation );
	}
}
