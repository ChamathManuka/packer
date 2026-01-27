package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.LoginData;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.ruser.OTPUser;
import com.travel.backpacker.repository.UserDao;

import java.util.Locale;
import java.util.Optional;

public abstract class AbstractAccessOperation extends AbstractOperation {
    protected AbstractAccessOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents) {
        super(userWrapper, requiredComponents);
    }

    protected Optional<? extends User> getUserFromLogin(LoginData loginData) {
        String username = loginData.getUsername().toLowerCase(Locale.ENGLISH);
        User.UserType type = loginData.getType();
        UserDao<? extends User> userDao = getUserDao(type);
        return userDao.findByUsername(username);
    }

    protected Optional<? extends User> getOTPUserLogin(OTPUser otpUser) {
        String username = otpUser.getUsername().toLowerCase(Locale.ENGLISH);
        User.UserType type = User.UserType.PASSENGER;
        UserDao<? extends User> userDao = getUserDao(type);
        return userDao.findByUsername(username);
    }
}
