package com.study.mangotv.user;

import com.study.mangotv.common.config.jwt.UserRegistrationJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserRegistrationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserRegistrationJwtProvider userRegistrationJwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = userRegistrationJwtProvider.resolveToken(request);

        if (token == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(this.makeTokenNullResponse(request.getRequestURI()));
            return false;
        } else if (!userRegistrationJwtProvider.validateToken(token)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(this.makeInvalidateTokenResponse(request.getRequestURI()));
            return false;
        }

        return true;
    }

    private String makeTokenNullResponse(String requestURI) {
        return "{\n" +
                "    \"httpStatus\": \"OK\",\n" +
                "    \"data\": {\n" +
                "        \"timestamp\": " + System.currentTimeMillis() + ",\n" +
                "        \"status\": 403,\n" +
                "        \"error\": \"Forbidden\",\n" +
                "        \"message\": \"token is null\",\n" +
                "        \"path\": \"" + requestURI + "\"\n" +
                "    },\n" +
                "    \"apiException\": null\n" +
                "}";
    }

    private String makeInvalidateTokenResponse(String requestURI) {
        return "{\n" +
                "    \"httpStatus\": \"OK\",\n" +
                "    \"data\": {\n" +
                "        \"timestamp\": " + System.currentTimeMillis() + ",\n" +
                "        \"status\": 403,\n" +
                "        \"error\": \"Forbidden\",\n" +
                "        \"message\": \"token is invalidate\",\n" +
                "        \"path\": \"" + requestURI + "\"\n" +
                "    },\n" +
                "    \"apiException\": null\n" +
                "}";
    }
}