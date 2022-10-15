package com.travel.backpacker.operations;

import com.travel.backpacker.dao.UserDao;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;

public abstract class AbstractOperation
{
	protected final UserWrapper userWrapper;
	protected final OperationRequiredComponents requiredComponents;

	public AbstractOperation( UserWrapper userWrapper, OperationRequiredComponents requiredComponents )
	{
		this.userWrapper = userWrapper;
		this.requiredComponents = requiredComponents;
	}

	protected UserDao getUserDao( User.UserType userType )
	{
		switch ( userType )
		{
			case admin:
				return requiredComponents.getAdminDao();
			case passenger:
				return requiredComponents.getPassengerDao();
			case operator:
				return requiredComponents.getOperatorDao();
			default:
				throw new RuntimeException();
		}
	}

}
