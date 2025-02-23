package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.LoginData;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserLoginAction;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.UnknownUser;
import com.travel.backpacker.dto.ruser.OTPUser;
import com.travel.backpacker.model.Passenger;
import com.travel.backpacker.service.mfa.MFAEmailService;
import com.travel.backpacker.service.mfa.OTPService;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class OTPLoginOperation extends AbstractAccessOperation implements Operation<UnknownUser>
{

	private final SCryptPasswordEncoder passwordEncorder = requiredComponents.getPasswordEncorder();

	private final UserLoginAction userLoginAction = requiredComponents.getUserLoginAction();

	private OTPUser otpUser;


	public OTPLoginOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents, OTPUser user)
	{
		super( userWrapper, requiredComponents );
		this.otpUser = user;
    }

	@Override
	public HttpEntity execute( UnknownUser unknownUser, Object... params )
	{
		User passenger = getOTPUserLogin(otpUser).orElseThrow(RuntimeException::new);
		if(passenger == null || StringUtils.isNullOrEmpty(passenger.getOtpCode()) || StringUtils.isNullOrEmpty(otpUser.getOtp()))
		{
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
		}
		if(passenger.getOtpCode().equals(String.valueOf(otpUser.getOtp())))
		{
			return new ResponseEntity( userLoginAction.getPackerToken(passenger), HttpStatus.OK );
		}

		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}

}
