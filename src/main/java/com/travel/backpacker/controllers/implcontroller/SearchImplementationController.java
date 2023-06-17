package com.travel.backpacker.controllers.implcontroller;

import com.travel.backpacker.controllers.SearchController;
import com.travel.backpacker.models.UnknownUser;
import org.springframework.stereotype.Component;

@Component("search.implementation.controller")
public class SearchImplementationController extends AbstractImplementationController<UnknownUser> implements SearchController {
}
