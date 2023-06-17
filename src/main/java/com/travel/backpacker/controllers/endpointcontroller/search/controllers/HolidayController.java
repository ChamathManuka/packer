package com.travel.backpacker.controllers.endpointcontroller.search.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public interface HolidayController {
    public HttpEntity execute();
}
