package com.travel.backpacker.Properties;

import org.springframework.core.env.Environment;

public abstract class AbstractPackerProperties implements PackerProperties
{
	private final Environment environment;

	public AbstractPackerProperties( Environment environment )
	{
		this.environment = environment;
	}

	@Override
	public String getProperty( String key )
	{
		return getProperty( key, "" );
	}

	private String getProperty( String key, String defaultValue, String... args )
	{
		String value = environment.getProperty( key );
		return value;
	}
}
