package com.travel.backpacker.service.mfa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MFAEmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendOTP(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP");
        message.setText("Your OTP for login is: " + otp + ". It expires in 5 minutes.");
        mailSender.send(message);

    }
}
