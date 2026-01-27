package com.travel.backpacker.service.mfa;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPService {
    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(90000);
        return String.valueOf(otp);
    }
}
