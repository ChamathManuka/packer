package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.UserWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;


public class AbstractSearchOperation extends AbstractOperation {
    protected AbstractSearchOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
        super(userWrapper, requiredComponents);
    }
}
