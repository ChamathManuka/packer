package com.travel.backpacker.controllers.exceptcontroller;

import com.travel.backpacker.controllers.SearchController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("search.exception.controller")
public class SearchExceptionHandlerController extends AbstractExceptionHandlerController<UnknownUser> implements SearchController {
    @Autowired
    protected SearchExceptionHandlerController(@Qualifier("search.logging.controller") SearchController controller) {
        super(controller);
    }
}
