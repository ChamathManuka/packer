package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.LoginData;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserLoginAction;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.model.Passenger;
import com.travel.backpacker.repository.PassengerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//unit testing example
@ExtendWith(MockitoExtension.class)
public class LoginOperationTest {

    @Mock
    private LoginData loginData;

    @Mock
    private SCryptPasswordEncoder scryptPasswordEncoder;

    @Mock
    private User user;

    @Mock
    private UserLoginAction userLoginAction;

    @Mock
    private OperationRequiredComponents operationRequiredComponents;

    @Mock
    private UserWrapper userWrapper;

    @Spy
    @InjectMocks
    private LoginOperation loginOperation;

    @Mock
    private PassengerDao passengerDao;

    @BeforeEach
    void setUp() {
        loginData = new LoginData();
        loginData.setUsername("john_doe");
        loginData.setPassword("password123".toCharArray());
        loginData.setType(User.UserType.PASSENGER);

        when(operationRequiredComponents.getPasswordEncorder()).thenReturn(scryptPasswordEncoder);
        when(operationRequiredComponents.getUserLoginAction()).thenReturn(userLoginAction);
        loginOperation = spy(new LoginOperation(userWrapper, operationRequiredComponents, loginData, "web"));
    }

    @Test
    void testLogin() {
        //arrange
        Passenger passenger = new Passenger();
        passenger.setUsername("john_doe");
        passenger.setPassword("password123".toCharArray());
        passenger.setActive(true);
        passenger.setUserType(User.UserType.PASSENGER);
        passenger.setOtpCode("123466");
        passenger.setOptExpiry(LocalDateTime.now().plusMinutes(5));

        // Override getUserFromLogin to return our passenger wrapped in an Optional
        doReturn(Optional.of(passenger)).when(loginOperation).getUserFromLogin(loginData);

        when(scryptPasswordEncoder.matches(any(CharSequence.class), eq("password123"))).thenReturn(true);

        doReturn(passengerDao.save(passenger)).when(loginOperation).saveUser(passenger);

        // Act: Execute the login operation
        HttpEntity result = loginOperation.execute(new UnknownUser());

        // Assert: Validate that the response is a successful OTP message
        assertNotNull(result);
        assertInstanceOf(ResponseEntity.class, result);
        ResponseEntity<?> response = (ResponseEntity<?>) result;
        assertNotNull(response.getBody());
        String body = response.getBody().toString();
        assertTrue(body.startsWith("OTP Sent!"));

    }
}
