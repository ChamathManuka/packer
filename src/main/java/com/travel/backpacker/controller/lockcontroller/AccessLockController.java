package com.travel.backpacker.controller.lockcontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.controller.Controller;
import com.travel.backpacker.dto.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("access.lock.controller")
public class AccessLockController<U extends User> extends AbstractLockController<U> implements AccessController<U>
{
	protected AccessLockController( @Qualifier("access.implementation.controller") Controller controller )
	{
		super( controller );
	}
}
