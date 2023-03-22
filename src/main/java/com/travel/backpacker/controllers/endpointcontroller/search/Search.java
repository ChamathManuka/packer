package com.travel.backpacker.controllers.endpointcontroller.search;

import com.travel.backpacker.controllers.AccessController;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.models.search.AccomSearch;
import com.travel.backpacker.operations.Operation;
import com.travel.backpacker.operations.factory.UnknownUserOperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@ResponseBody
@Controller
public class Search
{
	private final AccessController searchController;
	private final UnknownUserOperationFactory factory;

	@Autowired
	public Search( @Qualifier(value = "access.exception.controller") AccessController searchController, UnknownUserOperationFactory factory )
	{
		this.searchController = searchController;
		this.factory = factory;
	}

	@PostMapping("/search/accom")
	public HttpEntity search(@Valid AccomSearch accomSearch, @ModelAttribute UserWrapper userWrapper ){
		Operation<UnknownUser> operation = factory.getSearchOperation( accomSearch, userWrapper );
		return searchController.execute( userWrapper, operation );
	}
}
