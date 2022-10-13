package com.travel.backpacker.operations;

import com.sun.jdi.InternalException;
import com.travel.backpacker.controllers.endpointController.access.LoginData;
import com.travel.backpacker.models.UnknownUser;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserLoginAction;
import com.travel.backpacker.models.UserWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class LoginOperation extends AbstractAccessOperation implements Operation<UnknownUser>
{
	private final LoginData loginData;
	private final String platform;

	private final SCryptPasswordEncoder passwordEncorder = requiredComponents.getPasswordEncorder();

	private final UserLoginAction userLoginAction = requiredComponents.getUserLoginAction();

	private User user;

	public LoginOperation( UserWrapper userWrapper, OperationRequiredComponents requiredComponents, LoginData loginData, String platform )
	{
		super( userWrapper, requiredComponents );
		this.loginData = loginData;
		this.platform = platform;
	}

	@Override
	public HttpEntity execute( UnknownUser unknownUser )
	{
		user = getUserFromLogin( loginData ).orElseThrow( RuntimeException::new );
		try
		{
			CharSequence seq = java.nio.CharBuffer.wrap( loginData.getPassword() );
			if ( user.getPassword() != null && !passwordEncorder.matches( seq, String.valueOf( user.getPassword() ) ) )
			{
				throw new Exception( "password is wrong" );
			}
		}
		catch ( Exception exception )
		{
			throw new InternalException( "failed to login" );
		}
		System.out.println( "login operation in executed successfully" );

		if ( !user.isActive() )
		{
			throw new InternalException( "your account is inactive" );
		}
		return user.execute( userLoginAction, loginData, requiredComponents, userWrapper );
	}

}
