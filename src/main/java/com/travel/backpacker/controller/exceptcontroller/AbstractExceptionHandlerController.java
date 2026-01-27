package com.travel.backpacker.controller.exceptcontroller;

import com.travel.backpacker.controller.Controller;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.service.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

public abstract class AbstractExceptionHandlerController<U extends User> implements Controller<U> {
    private final Controller<U> controller;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractExceptionHandlerController(Controller<U> controller) {
        this.controller = controller;
    }

    @Override
    public HttpEntity execute(UserWrapper userWrapper, Operation<U> operation) {
        logger.info("Now operating : " + operation.getClass());
        return controller.execute(userWrapper, operation);
    }
}
