package com.travel.backpacker.controller.implcontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.dto.User;
import org.springframework.stereotype.Component;

@Component("access.implementation.controller")
public class AccessImplementationController<U extends User> extends AbstractImplementationController<U> implements AccessController<U> {
}
