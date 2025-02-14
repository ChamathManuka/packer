package com.travel.backpacker.service.content;

import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.rcontentdownload.RContentType;
import com.travel.backpacker.service.operations.AbstractOperation;
import com.travel.backpacker.service.operations.OperationRequiredComponents;

abstract class AbstractContentDownloadOperation extends AbstractOperation
{
    protected AbstractContentDownloadOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
        super(userWrapper, requiredComponents);
    }

    protected String getContentType(RContentType contentType)
    {
        return contentType.getType();
    }
}
