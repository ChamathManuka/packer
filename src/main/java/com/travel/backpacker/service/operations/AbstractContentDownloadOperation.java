package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.rcontentdownload.RContentType;

abstract class AbstractContentDownloadOperation extends AbstractOperation {
    protected AbstractContentDownloadOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
        super(userWrapper, requiredComponents);
    }

    protected String getContentType(RContentType contentType) {
        return contentType.getType();
    }
}
