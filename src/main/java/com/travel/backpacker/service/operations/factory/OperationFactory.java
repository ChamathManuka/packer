package com.travel.backpacker.service.operations.factory;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.service.operations.OperationRequiredComponents;

public abstract class OperationFactory<U extends User>
{
	final OperationRequiredComponents operationRequiredComponents;

	protected OperationFactory( OperationRequiredComponents operationRequiredComponents )
	{
		this.operationRequiredComponents = operationRequiredComponents;
	}
}
