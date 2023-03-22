package com.travel.backpacker.schedulers.content;

import com.travel.backpacker.operations.OperationRequiredComponents;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ContentDownloader
{
	protected final OperationRequiredComponents operationRequiredComponents;

	@Autowired
	protected ContentDownloader( OperationRequiredComponents operationRequiredComponents )
	{
		this.operationRequiredComponents = operationRequiredComponents;
	}
}
