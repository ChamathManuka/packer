package com.travel.backpacker.model;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserAction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Passenger")
public class Passenger implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private char[] password;

    @Column(columnDefinition = "varchar(255) default 'GPS'", nullable = false)
    private String platform;

    @Column
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private boolean mfaEnabled; // Whether MFA is enabled
    @Column
    private LocalDateTime optExpiry;
    @Column
    private String otpCode;     // Used for Email/SMS OTP

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AddressId", unique = true)
    private Address address = new Address();

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public UserType getUserType() {
        return UserType.PASSENGER;
    }

    @Override
    public void setUserType(UserType userType) {

    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public void setMfaEnabled(boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }

    public LocalDateTime getOptExpiry() {
        return optExpiry;
    }

    public void setOptExpiry(LocalDateTime optExpiry) {
        this.optExpiry = optExpiry;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    @Override
    public HttpEntity execute(UserAction userAction, Object... args) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
