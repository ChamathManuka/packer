package com.travel.backpacker.controller.implcontroller;

import com.travel.backpacker.controller.Controller;
import com.travel.backpacker.dto.iuser.AdminUser;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.service.content.ContentDownloadOperation;
import com.travel.backpacker.service.operations.AdminRegisterOperation;
import com.travel.backpacker.service.operations.LoginOperation;
import com.travel.backpacker.service.operations.Operation;
import com.travel.backpacker.service.operations.PassengerRegisterOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

public abstract class AbstractImplementationController<U extends User> implements Controller<U>
{
	Logger logger = LoggerFactory.getLogger( this.getClass() );
	Controller<U> controller;
	@Override
	public HttpEntity execute( UserWrapper userWrapper, Operation<U> operation )
	{
		if(operation instanceof LoginOperation)
		{
			logger.info( "Now operating : " + operation.getClass() );
			return operation.execute( ( U ) new UnknownUser() );
		}
		if(operation instanceof AdminRegisterOperation)
		{
			logger.info( "Now operating : " + operation.getClass() );
			return operation.execute( ( U ) new AdminUser() );
		}
		if(operation instanceof PassengerRegisterOperation )
		{
			logger.info( "Now operating : " + operation.getClass() );
			return operation.execute( ( U ) new UnknownUser() );
		}
		if(operation instanceof ContentDownloadOperation)
		{
			logger.info( "Now operating : " + operation.getClass() );
			AdminUser adminUser = new AdminUser();
			adminUser.setId(userWrapper.getAdminId());
			adminUser.setUserType(User.UserType.ADMIN);
			return operation.execute((U) adminUser);
		}
		return null;

	}
}
