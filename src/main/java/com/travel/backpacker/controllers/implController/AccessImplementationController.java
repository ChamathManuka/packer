package com.travel.backpacker.controllers.implController;

import com.travel.backpacker.controllers.AccessController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.stereotype.Component;

@Component("access.implementation.controller")
public class AccessImplementationController extends AbstractImplementationController<UnknownUser> implements AccessController
{
	//implementation goes here for operation.execute
}
