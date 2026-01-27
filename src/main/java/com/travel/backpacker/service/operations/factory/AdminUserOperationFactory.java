package com.travel.backpacker.service.operations.factory;

import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.AdminUser;
import com.travel.backpacker.dto.rcontentdownload.RContentType;
import com.travel.backpacker.dto.ruser.RAdmin;
import com.travel.backpacker.service.operations.AdminRegisterOperation;
import com.travel.backpacker.service.operations.ContentDownloadOperation;
import com.travel.backpacker.service.operations.Operation;
import com.travel.backpacker.service.operations.OperationRequiredComponents;
import org.springframework.stereotype.Component;

@Component
public class AdminUserOperationFactory extends OperationFactory<AdminUser> {
    protected AdminUserOperationFactory(OperationRequiredComponents operationRequiredComponents) {
        super(operationRequiredComponents);
    }

    public Operation<AdminUser> getContentDownloadOperation(UserWrapper userWrapper, RContentType type) {
        return new ContentDownloadOperation(userWrapper, operationRequiredComponents, type);
    }

    public Operation<AdminUser> getAdminRegisterOperation(RAdmin rAdmin, UserWrapper userWrapper, String platform) {
        return new AdminRegisterOperation(userWrapper, operationRequiredComponents, rAdmin, platform);
    }
}
