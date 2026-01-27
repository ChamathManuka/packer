package com.travel.backpacker.service.interceptors;

import com.travel.backpacker.properties.DefaultPackerProperties;
import com.travel.backpacker.dto.User;
import com.travel.backpacker.dto.UserWrapper;
import io.jsonwebtoken.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAuthInterceptor implements HandlerInterceptor {
    private final DefaultPackerProperties packerProperties;
    private final byte[] key;

    protected AbstractAuthInterceptor(DefaultPackerProperties packerProperties) {
        this.packerProperties = packerProperties;
        key = packerProperties.getKey().getBytes(StandardCharsets.UTF_8);

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    public Map<String, UserWrapper> createPackerPayload(UserWrapper userWrapper) {
        Map<String, UserWrapper> payload = new HashMap<>();
        payload.put("payload", userWrapper);
        return payload;
    }

    public UserWrapper updateWrapper(User user) {
        UserWrapper userWrapper = new UserWrapper.Builder().buildWrapper();
        userWrapper.getRoles().add(user.getUserType());
        switch (user.getUserType()) {
            case ADMIN:
                userWrapper.setAdminId(user.getId());
                break;
            case PASSENGER:
                userWrapper.setPassengerId(user.getId());
                break;
            case OPERATOR:
                userWrapper.setOperatorId(user.getId());
                break;
            case SUPPLIER:
                userWrapper.setSupplierId(user.getId());
                break;
            case UNKNOWNUSER:
                userWrapper.setUnknownUserId(user.getId());
                break;
        }
        userWrapper.setActive(user.isActive());
        return userWrapper;
    }

    public void updateWrapper(String token_subject, HttpServletRequest request) {
        UserWrapper userWrapper = (UserWrapper) request.getAttribute("userWrapper");
        userWrapper.setAdminId(Long.parseLong(token_subject.split("-")[1]));
        userWrapper.setActive(true);
        userWrapper.getRoles().add(User.UserType.ADMIN);
        request.setAttribute("userWrapper", userWrapper);

    }


    public String getPackerToken(User user) {
        try {
            UserWrapper wrapper = this.updateWrapper(user);

            String subject = user.getUserType().name() + "-" + user.getId();
            java.util.Date now = Date.from(ZonedDateTime.now().toInstant());
            java.util.Date expiryDate = Date.from(ZonedDateTime.now().plus(1, ChronoUnit.HOURS).toInstant());


            JwtBuilder jwtBuilder = Jwts.builder().setExpiration(expiryDate).setSubject(subject).setIssuedAt(now).signWith(SignatureAlgorithm.HS256, key);
            Map<String, UserWrapper> payload = createPackerPayload(wrapper);
            payload.forEach(jwtBuilder::claim);
            return jwtBuilder.compact();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkTokenValidity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid token");
            return false;
        }
        try {

            String jwt = token.substring(7);  // Remove "Bearer " prefix
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);  // Validate the token
            return true;  // Token is valid, proceed with the request
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return false;
        }
    }

    public String getUserIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace("Bearer ", "")).getBody();
        return claims.getSubject();
    }
}
