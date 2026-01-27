package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.model.Passenger;
import com.travel.backpacker.repository.UserDao;

public abstract class AbstractOperation {
    protected final UserWrapper userWrapper;
    protected final OperationRequiredComponents requiredComponents;

    protected AbstractOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
        this.userWrapper = userWrapper;
        this.requiredComponents = requiredComponents;
    }

    protected UserDao getUserDao(User.UserType userType) {
        switch (userType) {
            case ADMIN:
                return requiredComponents.getAdminDao();
            case PASSENGER:
                return requiredComponents.getPassengerDao();
            case OPERATOR:
                return requiredComponents.getOperatorDao();
            default:
                throw new RuntimeException();
        }
    }

    protected Passenger saveUser(Passenger user) {
        return requiredComponents.getPassengerDao().save(user);
    }
}
