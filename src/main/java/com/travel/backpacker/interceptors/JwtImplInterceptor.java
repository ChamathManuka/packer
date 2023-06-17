package com.travel.backpacker.interceptors;

import com.travel.backpacker.properties.DefaultPackerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtImplInterceptor extends AbstractJwtInterceptor
{
	@Autowired
	public JwtImplInterceptor( DefaultPackerProperties packerProperties )
	{
		super( packerProperties );
	}

}
