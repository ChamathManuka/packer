package com.travel.backpacker.controller.contentcontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.dto.iuser.AdminUser;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.rcontentdownload.RContentType;
import com.travel.backpacker.service.operations.Operation;
import com.travel.backpacker.service.operations.factory.AdminUserOperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccommodationContentController
{
    private final AccessController<AdminUser> accessController;
    private final AdminUserOperationFactory adminUserOperationFactory;

    @Autowired
    protected AccommodationContentController(@Qualifier("access.exception.controller") AccessController<AdminUser> accessController, AdminUserOperationFactory adminUserOperationFactory) {
        this.accessController = accessController;
        this.adminUserOperationFactory = adminUserOperationFactory;
    }


    @PostMapping("admin/content/download/accom")
    public HttpEntity accomDownload(@RequestAttribute("userWrapper") UserWrapper wrapper, @Valid @RequestBody RContentType type)
    {
        Operation<AdminUser> operation = adminUserOperationFactory.getContentDownloadOperation(wrapper,type );
        return accessController.execute( wrapper, operation );
    }
}
