package com.example.Interceptors;

import com.example.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProvider jwtProvider;

    private final String[] AllowApi = {"/authen", "/login?error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
        String url = request.getRequestURI();
        String method = request.getMethod();
        String authorizationHeader = request.getHeader("Authorization");

        if(AllowApiInterceptor(request)){
            return true;
        }

        if(authorizationHeader != null ){
            if(jwtProvider.CheckToken(HttpServletRequest request)){
                return true;
            }
            response.getWriter().write("error invaild token");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            return false;
        }

        return true;
    }


    public boolean AllowApiInterceptor(HttpServletRequest request){
        for(String allowApi : AllowApi){
            if(request.getRequestURI().contains(allowApi)){
                return true;
            }
        }
        return false;
    }
}
