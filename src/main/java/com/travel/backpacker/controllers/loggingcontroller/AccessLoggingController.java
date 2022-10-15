package com.travel.backpacker.controllers.loggingcontroller;

import com.travel.backpacker.controllers.AccessController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("access.logging.controller")
public class AccessLoggingController extends AbstractLoggingController<UnknownUser> implements AccessController
{

	@Autowired
	protected AccessLoggingController( @Qualifier("access.lock.controller") AccessController controller )
	{
		super( controller );
	}
}
