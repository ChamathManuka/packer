package com.travel.backpacker.encrypt;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class PasswordEncorder implements ApplicationContextAware
{
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	@Bean
	public SCryptPasswordEncoder createPasswordEncorder()
	{
		return new SCryptPasswordEncoder();
	}
}
