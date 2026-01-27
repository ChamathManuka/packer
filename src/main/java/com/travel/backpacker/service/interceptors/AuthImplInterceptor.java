package com.travel.backpacker.service.interceptors;

import com.travel.backpacker.properties.DefaultPackerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthImplInterceptor extends AbstractAuthInterceptor {

    protected AuthImplInterceptor(DefaultPackerProperties packerProperties) {
        super(packerProperties);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/login") || requestURI.startsWith("/register") || requestURI.startsWith("/verify-otp")) {
            return true;
        }
        return checkTokenValidity(request, response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


}
