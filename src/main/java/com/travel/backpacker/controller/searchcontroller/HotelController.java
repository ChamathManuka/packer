package com.travel.backpacker.controller.searchcontroller;

import com.travel.backpacker.controller.AccessController;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.rsearch.accommodatioon.HotelDTO;
import com.travel.backpacker.service.operations.Operation;
import com.travel.backpacker.service.operations.factory.UnknownUserOperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotels")
public class HotelController {
    private final AccessController<UnknownUser> accessController;
    private final UnknownUserOperationFactory unknownUserOperationFactory;

    @Autowired
    public HotelController(@Qualifier("access.exception.controller") AccessController<UnknownUser> accessController, UnknownUserOperationFactory unknownUserOperationFactory) {
        this.accessController = accessController;
        this.unknownUserOperationFactory = unknownUserOperationFactory;
    }

    @GetMapping("/search")
    public HttpEntity searchHotels(@RequestParam(required = false) String city, @RequestAttribute("userWrapper") UserWrapper userWrapper) {
        Operation<UnknownUser> operation = unknownUserOperationFactory.getHotelSearchOperation(userWrapper);
        return accessController.execute(userWrapper, operation);
    }
}
