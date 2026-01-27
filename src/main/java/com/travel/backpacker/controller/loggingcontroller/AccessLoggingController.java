package com.travel.backpacker.controller.loggingcontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("access.logging.controller")
public class AccessLoggingController<U extends User> extends AbstractLoggingController<U> implements AccessController<U> {

    @Autowired
    protected AccessLoggingController(@Qualifier("access.lock.controller") AccessController<U> controller) {
        super(controller);
    }
}
