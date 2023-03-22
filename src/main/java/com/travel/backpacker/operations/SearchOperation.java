package com.travel.backpacker.operations;

import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.models.search.GlobalSearch;
import org.springframework.http.HttpEntity;

public class SearchOperation extends AbstractSearchOperation implements Operation<UnknownUser>
{
	private final GlobalSearch globalSearch;

	public SearchOperation( UserWrapper userWrapper, OperationRequiredComponents requiredComponents, GlobalSearch globalSearch )
	{
		super( userWrapper, requiredComponents );
		this.globalSearch = globalSearch;
	}

	@Override
	public HttpEntity execute( UnknownUser unknownUser )
	{
		return createSearch( globalSearch ).execute();
	}
}
