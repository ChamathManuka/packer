package com.travel.backpacker.controller.exceptcontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("access.exception.controller")
public class AccessExceptionHandlerController<U extends User> extends AbstractExceptionHandlerController<U> implements AccessController<U> {
    @Autowired
    public AccessExceptionHandlerController(@Qualifier("access.logging.controller") AccessController<U> controller) {
        super(controller);
    }
}
