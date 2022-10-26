package com.travel.backpacker.operations;

import com.travel.backpacker.models.User;
import org.springframework.http.HttpEntity;

public interface Operation<T extends User>
{
	HttpEntity execute( T t );
}
//very common methods