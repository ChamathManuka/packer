package com.travel.backpacker.dto;

import org.springframework.http.HttpEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface User extends UserAction<HttpEntity>, Serializable {
    Long getId();

    void setId(Long id);

    public UserType getUserType();

    void setUserType(UserType userType);

    String getUsername();

    void setUsername(String username);

    String getOtpCode();

    void setOtpCode(String otpCode);

    void setOptExpiry(LocalDateTime optExpiry);

    LocalDateTime getOptExpiry();

    public char[] getPassword();

    public boolean isActive();

    public enum UserType {
        UNKNOWNUSER, OPERATOR, PASSENGER, ADMIN, SUPPLIER;
    }

    public enum Gender {
        MALE, FEMALE, NOTPROVIDED;
    }

}
