package com.travel.backpacker.controllers.endpointcontroller.search.controllers;

import com.travel.backpacker.controllers.endpointcontroller.search.handlers.AccomHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class AccomController
{
	private final AccomHandler accomHandler;

	@Autowired
	public AccomController( AccomHandler accomHandler )
	{
		this.accomHandler = accomHandler;
	}

	public HttpEntity execute()
	{
		return accomHandler.execute();
	}
}
