package com.travel.backpacker.controllers.lockcontroller;

import com.travel.backpacker.controllers.AccessController;
import com.travel.backpacker.controllers.Controller;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("access.lock.controller")
public class AccessLockController extends AbstractLockController<UnknownUser> implements AccessController
{
	protected AccessLockController( @Qualifier("access.implementation.controller") Controller controller )
	{
		super( controller );
	}
}
