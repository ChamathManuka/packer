package com.travel.backpacker.service.mfa;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Service;

@Service
public class MFAService {
    private final GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

    public String generateMFA() {
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
        return googleAuthenticatorKey.getKey();
    }

    public boolean isValidOTP(String secret, int otp) {
        return googleAuthenticator.authorize(secret, otp);
    }
}
