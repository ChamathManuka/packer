package com.travel.backpacker.models.search;

import com.travel.backpacker.controllers.endpointcontroller.search.controllers.AccomController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class AccomSearch implements GlobalSearch {
    @Autowired
    private final AccomController accomController;

    public AccomSearch(AccomController accomController) {
        this.accomController = accomController;
    }

    @Override
    public HttpEntity execute() {
        return accomController.execute();
    }
}
