package com.travel.backpacker.service.interceptors;

import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.rsearch.RSearch;
import com.travel.backpacker.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class SearchInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        RSearch rSearch = new RSearch();
        rSearch.setOperationType(RSearch.OperationType.ACCOMMODATION);
        rSearch.setCity(request.getParameter("city"));
        UserWrapper userWrapper = (UserWrapper) request.getAttribute("userWrapper");
        userWrapper.setrSearch(rSearch);
        request.setAttribute("userWrapper", userWrapper);
        return true;

    }
}
