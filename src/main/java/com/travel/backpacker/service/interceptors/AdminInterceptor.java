package com.travel.backpacker.service.interceptors;


import com.travel.backpacker.properties.DefaultPackerProperties;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor extends AbstractAuthInterceptor {
    protected AdminInterceptor(DefaultPackerProperties packerProperties) {
        super(packerProperties);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token_subject = getUserIdFromToken(request);
        if (StringUtils.isNotNullOrEmpty(token_subject) && token_subject.startsWith("ADMIN")) {
            updateWrapper(token_subject, request);
            return checkTokenValidity(request, response);
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // Set HTTP 403 Forbidden
        response.getWriter().write("Error: Authorization failed");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
