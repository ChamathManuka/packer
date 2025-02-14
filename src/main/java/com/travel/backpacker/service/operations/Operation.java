package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.User;
import org.springframework.http.HttpEntity;

public interface Operation<T extends User>
{
	HttpEntity execute( T t );
}
//very common methods