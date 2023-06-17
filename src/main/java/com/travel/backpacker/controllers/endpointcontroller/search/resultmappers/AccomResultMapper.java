package com.travel.backpacker.controllers.endpointcontroller.search.resultmappers;

import com.travel.backpacker.schedulers.content.AccomContentDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class AccomResultMapper
{
	AccomContentDownloader accomContentDownloader;

	@Autowired
	public AccomResultMapper(AccomContentDownloader accomContentDownloader)
	{
		this.accomContentDownloader = accomContentDownloader;
	}
	public HttpEntity execute()
	{
		return accomContentDownloader.execute();
	}
}
