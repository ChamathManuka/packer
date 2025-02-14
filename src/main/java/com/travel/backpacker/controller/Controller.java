package com.travel.backpacker.controller;

import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.service.operations.Operation;
import org.springframework.http.HttpEntity;

public interface Controller<U extends User>
{
	HttpEntity execute( UserWrapper userWrapper, Operation<U> operation );
}
