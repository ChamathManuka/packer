package com.travel.backpacker.service.operations;

import com.travel.backpacker.repository.UserDao;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

public abstract class AbstractOperation
{
	protected final UserWrapper userWrapper;
	protected final OperationRequiredComponents requiredComponents;

	protected AbstractOperation( UserWrapper userWrapper, OperationRequiredComponents requiredComponents )
	{
		this.userWrapper = userWrapper;
		this.requiredComponents = requiredComponents;
	}

	protected UserDao getUserDao( User.UserType userType )
	{
		switch ( userType )
		{
			case ADMIN:
				return requiredComponents.getAdminDao();
			case PASSENGER:
				return requiredComponents.getPassengerDao();
			case OPERATOR:
				return requiredComponents.getOperatorDao();
			default:
				throw new RuntimeException();
		}
	}
}
