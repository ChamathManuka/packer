package com.travel.backpacker.controllers.loggingcontroller;

import com.travel.backpacker.controllers.SearchController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("search.logging.controller")
public class SearchLoggingController extends AbstractLoggingController<UnknownUser> implements SearchController {
    @Autowired
    protected SearchLoggingController(@Qualifier("search.lock.controller") SearchController controller) {
        super(controller);
    }
}
