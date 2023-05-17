package com.ssafy.ssafit.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class jwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "access-token";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(request.getMethod().equals("OPTIONS"))
            return true;

        String token = request.getHeader(HEADER_AUTH);

        if (token != null) {
            jwtUtil.valid(token);
            return true; // 진행시켜
        }

        throw new Exception("유효하지 않는 접근이다.");

    }

}