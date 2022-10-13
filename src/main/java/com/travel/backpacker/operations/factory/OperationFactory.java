package com.travel.backpacker.operations.factory;

import com.travel.backpacker.models.User;
import com.travel.backpacker.operations.OperationRequiredComponents;

public abstract class OperationFactory<U extends User>
{
	final OperationRequiredComponents operationRequiredComponents;

	protected OperationFactory( OperationRequiredComponents operationRequiredComponents )
	{
		this.operationRequiredComponents = operationRequiredComponents;
	}
}
