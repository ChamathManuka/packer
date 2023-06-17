package com.travel.backpacker.controllers.lockcontroller;

import com.travel.backpacker.controllers.SearchController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("search.lock.controller")
public class SearchLockController extends AbstractLockController<UnknownUser> implements SearchController {
    @Autowired
    protected SearchLockController(@Qualifier("search.implementation.controller") SearchController controller) {
        super(controller);
    }
}
