package com.travel.backpacker.service.operations.factory;

import com.travel.backpacker.dto.*;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.ruser.OTPUser;
import com.travel.backpacker.dto.ruser.RPassenger;
import com.travel.backpacker.service.operations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnknownUserOperationFactory extends OperationFactory<UnknownUser> {
    @Autowired
    protected UnknownUserOperationFactory(OperationRequiredComponents operationRequiredComponents) {
        super(operationRequiredComponents);
    }

    public Operation<UnknownUser> getLoginOperation(LoginData loginData, UserWrapper userWrapper, String platform) {
        return new LoginOperation(userWrapper, operationRequiredComponents, loginData, platform);
    }

    public Operation<UnknownUser> getOTPLoginOperation(UserWrapper userWrapper, OTPUser otpUser) {
        return new OTPLoginOperation(userWrapper, operationRequiredComponents, otpUser);
    }

    public Operation<UnknownUser> getPassengerRegisterOperation(RPassenger rPassenger, UserWrapper userWrapper, String platform) {
        return new PassengerRegisterOperation(userWrapper, operationRequiredComponents, rPassenger, platform);
    }

    public Operation<UnknownUser> getHotelSearchOperation(UserWrapper userWrapper) {
        return new HotelSearchOperation(userWrapper, operationRequiredComponents);
    }
}
