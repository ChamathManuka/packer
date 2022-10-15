package com.travel.backpacker.controllers;

import com.travel.backpacker.models.User;
import com.travel.backpacker.models.UserWrapper;
import com.travel.backpacker.operations.Operation;
import org.springframework.http.HttpEntity;

public interface Controller<U extends User>
{
	HttpEntity execute( UserWrapper userWrapper, Operation<U> operation );
}
