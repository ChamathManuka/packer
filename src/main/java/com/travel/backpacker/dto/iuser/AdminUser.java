package com.travel.backpacker.dto.iuser;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserAction;
import org.springframework.http.HttpEntity;

import java.time.LocalDateTime;

public class AdminUser implements User {
    private String username;
    private String password;
    private long id;
    private boolean active;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public void setUserType(UserType userType) {

    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getOtpCode() {
        return "";
    }

    @Override
    public void setOtpCode(String otpCode) {

    }

    @Override
    public void setOptExpiry(LocalDateTime optExpiry) {

    }

    @Override
    public LocalDateTime getOptExpiry() {
        return null;
    }

    @Override
    public char[] getPassword() {
        return this.password.toCharArray();
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public HttpEntity execute(UserAction userAction, Object... args) {
        return null;
    }
}
