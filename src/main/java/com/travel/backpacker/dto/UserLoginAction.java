package com.travel.backpacker.dto;

import com.travel.backpacker.service.interceptors.AuthImplInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserLoginAction implements UserAction<HttpEntity> {
    private final AuthImplInterceptor jwtInterceptor;

    @Autowired
    public UserLoginAction(AuthImplInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public HttpEntity execute(UserAction userAction, Object... args) {
        return new ResponseEntity(getPackerToken((User) userAction), HttpStatus.OK);
    }

    public String getPackerToken(User user) {
        return jwtInterceptor.getPackerToken(user);
    }
}
