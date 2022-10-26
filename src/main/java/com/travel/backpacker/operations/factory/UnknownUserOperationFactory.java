package com.travel.backpacker.operations.factory;

import com.travel.backpacker.controllers.endpointcontroller.access.LoginData;
import com.travel.backpacker.models.ruser.RPassenger;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.LoginOperation;
import com.travel.backpacker.operations.Operation;
import com.travel.backpacker.operations.OperationRequiredComponents;
import com.travel.backpacker.operations.RegisterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnknownUserOperationFactory extends OperationFactory<UnknownUser>
{
	@Autowired
	protected UnknownUserOperationFactory( OperationRequiredComponents operationRequiredComponents )
	{
		super( operationRequiredComponents );
	}

	public Operation<UnknownUser> getLoginOperation( LoginData loginData, UserWrapper userWrapper, String platform )
	{
		return new LoginOperation( userWrapper, operationRequiredComponents, loginData, platform );
	}

	public Operation<UnknownUser> getRegisterOperation( RPassenger rPassenger, UserWrapper userWrapper, String platform )
	{
		return new RegisterOperation( userWrapper, operationRequiredComponents, rPassenger, platform );
	}
}
