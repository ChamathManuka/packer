package com.travel.backpacker.controllers.endpointcontroller.search.handlers;

import com.travel.backpacker.controllers.endpointcontroller.search.resultmappers.AccomResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class AccomHandler
{
	private final AccomResultMapper accomResultMapper;

	@Autowired
	public AccomHandler( AccomResultMapper accomResultMapper )
	{
		this.accomResultMapper = accomResultMapper;
	}

	public HttpEntity execute()
	{
		return accomResultMapper.execute();
	}
}
