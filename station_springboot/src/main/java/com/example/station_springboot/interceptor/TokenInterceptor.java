package com.example.station_springboot.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    //后端的拦截器  也是拦截除了登陆外的其他请求 并且验证 token的有效性
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        System.out.println("url is " + url);
        if (url.contains("/login") || request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            String token = request.getHeader("Token");
            if (token != null) {
                System.out.println("Token is" + token);
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

        }


    }
}