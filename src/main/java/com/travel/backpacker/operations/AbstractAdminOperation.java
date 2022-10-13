package com.travel.backpacker.operations;

import com.travel.backpacker.models.UserWrapper;

public class AbstractAdminOperation extends AbstractOperation
{
	public AbstractAdminOperation( UserWrapper userWrapper, OperationRequiredComponents requiredComponents )
	{
		super( userWrapper, requiredComponents );
	}
}
