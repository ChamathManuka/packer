package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.LoginData;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserLoginAction;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.model.Passenger;
import com.travel.backpacker.service.mfa.MFAEmailService;
import com.travel.backpacker.service.mfa.OTPService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.time.LocalDateTime;

public class LoginOperation extends AbstractAccessOperation implements Operation<UnknownUser> {
    private final LoginData loginData;
    private final String platform;

    private final SCryptPasswordEncoder passwordEncorder = requiredComponents.getPasswordEncorder();

    private final UserLoginAction userLoginAction = requiredComponents.getUserLoginAction();

    private User user;

    private final MFAEmailService mfaEmailService;

    public LoginOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents, LoginData loginData, String platform) {
        super(userWrapper, requiredComponents);
        this.loginData = loginData;
        this.platform = platform;
        this.mfaEmailService = requiredComponents.getMFAEmailService();
    }

    @Override
    public HttpEntity execute(UnknownUser unknownUser, Object... params) {
        user = getUserFromLogin(loginData).orElseThrow(RuntimeException::new);
        try {
            CharSequence seq = java.nio.CharBuffer.wrap(loginData.getPassword());
            if (user.getPassword() != null && !passwordEncorder.matches(seq, String.valueOf(user.getPassword()))) {
                throw new Exception("password is wrong");
            }
        } catch (Exception exception) {
            throw new RuntimeException("failed to login");
        }

        if (!user.isActive()) {
            throw new RuntimeException("your account is inactive");
        }

        String otp = OTPService.generateOTP();
        user.setOtpCode(otp);
        user.setOptExpiry(LocalDateTime.now().plusMinutes(5));
        User.UserType type = loginData.getType();
        if (type == User.UserType.PASSENGER) {
            Passenger passenger = (Passenger) user;
            saveUser(passenger);
//			mfaEmailService.sendOTP(passenger.getEmail(), otp);
            return new ResponseEntity<>("OTP Sent! " + otp, HttpStatus.OK);
        }

        return userLoginAction.execute(user, loginData, requiredComponents, userWrapper);
    }

}
