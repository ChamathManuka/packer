package com.travel.backpacker.controllers.endpointcontroller.search;

import com.travel.backpacker.controllers.SearchController;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import com.travel.backpacker.operations.factory.UnknownUserOperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class Search {
    private final SearchController searchController;
    private final UnknownUserOperationFactory factory;

    @Autowired
    public Search(@Qualifier(value = "search.exception.controller") SearchController searchController, UnknownUserOperationFactory factory) {
        this.searchController = searchController;
        this.factory = factory;
    }

    @PostMapping("/search")
    public HttpEntity search(@RequestParam(value = "type", defaultValue = "global") String type, @ModelAttribute UserWrapper userWrapper) {

        Operation<UnknownUser> operation = factory.getSearchOperation(type, userWrapper);
        return searchController.execute(userWrapper, operation);
    }
}
