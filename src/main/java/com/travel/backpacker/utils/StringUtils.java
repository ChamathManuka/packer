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

	public static boolean canBeLong(String str) {
		return str != null && str.matches("-?\\d+");
	}

	public static boolean canBeInt(String str) {
		return str != null && str.matches("-?\\d+");
	}
}
