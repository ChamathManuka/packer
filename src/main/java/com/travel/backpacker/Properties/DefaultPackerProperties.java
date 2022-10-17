package com.travel.backpacker.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("packer.properties")
@PropertySource("classpath:/properties/packer.properties")
public class DefaultPackerProperties extends AbstractPackerProperties
{
	@Value("${timezone}")
	private String timezone;
	@Value("${aws.s3.secret_key_id}")
	private String awsSecretKey;
	@Value("${aws.s3.access_key_id}")
	private String awsAccessKey;

	@Value( "$jwt.key" )
	private String key;

	@Autowired
	public DefaultPackerProperties( Environment environment )
	{
		super( environment );
	}

	public String getTimezone()
	{
		return timezone;
	}

	public void setTimezone( String timezone )
	{
		this.timezone = timezone;
	}

	public String getAwsSecretKey()
	{
		return awsSecretKey;
	}

	public void setAwsSecretKey( String awsSecretKey )
	{
		this.awsSecretKey = awsSecretKey;
	}

	public String getAwsAccessKey()
	{
		return awsAccessKey;
	}

	public void setAwsAccessKey( String awsAccessKey )
	{
		this.awsAccessKey = awsAccessKey;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey( String key )
	{
		this.key = key;
	}
}
