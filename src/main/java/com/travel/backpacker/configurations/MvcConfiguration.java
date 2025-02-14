package com.travel.backpacker.configurations;

import com.travel.backpacker.service.interceptors.AddressInterceptor;
import com.travel.backpacker.service.interceptors.AdminInterceptor;
import com.travel.backpacker.service.interceptors.AuthImplInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer
{
	private final AuthImplInterceptor authImplInterceptor;
	private final AdminInterceptor adminInterceptor;

    public MvcConfiguration(AuthImplInterceptor authImplInterceptor, AdminInterceptor adminInterceptor) {
        this.authImplInterceptor = authImplInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
	public void addInterceptors( InterceptorRegistry registry )
	{
		registry.addInterceptor( new AddressInterceptor() );

		registry.addInterceptor(authImplInterceptor).excludePathPatterns("/error");

		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
	}
}
