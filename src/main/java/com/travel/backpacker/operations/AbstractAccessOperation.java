package com.travel.backpacker.operations;

import com.travel.backpacker.controllers.endpointcontroller.access.LoginData;
import com.travel.backpacker.dao.UserDao;
import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;

import java.util.Locale;
import java.util.Optional;

public abstract class AbstractAccessOperation extends AbstractOperation
{
	protected AbstractAccessOperation( UserWrapper userWrapper, OperationRequiredComponents requiredComponents )
	{
		super( userWrapper, requiredComponents );
	}

	protected Optional<? extends User> getUserFromLogin( LoginData loginData )
	{
		String username = loginData.getUsername().toLowerCase( Locale.ENGLISH );
		User.UserType type = loginData.getType();
		UserDao<? extends User> userDao = getUserDao( type );
		return userDao.findByUsername( username );
	}
}
