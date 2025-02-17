package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


public interface Operation<T extends User>
{
	HttpEntity execute( T t, Object... params );
}
//very common methods