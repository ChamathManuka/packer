package com.travel.backpacker.controllers.exceptcontroller;

import com.travel.backpacker.controllers.AccessController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("access.exception.controller")
public class AccessExceptionHandlerController extends AbstractExceptionHandlerController<UnknownUser> implements AccessController
{
	@Autowired
	public AccessExceptionHandlerController( @Qualifier("access.logging.controller") AccessController controller )
	{
		super( controller );
	}
}
