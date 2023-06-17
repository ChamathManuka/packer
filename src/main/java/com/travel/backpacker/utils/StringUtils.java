package com.travel.backpacker.utils;

public class StringUtils
{
	public static boolean isNullOrEmpty( String value )
	{
		if(value == null )
		{
			return true;
		}
		else
		{
			return value.isEmpty();
		}
	}

	public static boolean isNotNullOrEmpty(String value )
	{
		return !isNullOrEmpty( value );
	}
}
