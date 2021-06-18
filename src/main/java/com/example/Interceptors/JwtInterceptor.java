package com.example.Interceptors;

import com.example.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("kang");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("as");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
        String url = request.getRequestURI();
        String method = request.getMethod();
        String authorizationHeader = request.getHeader("Authorization");
        if(url.contains("authen")){
        return true;
        }

        if(authorizationHeader != null ){
            if(jwtService.CheckToken(authorizationHeader)){
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



//    public static String resolveToken(HttpServletRequest req) {
//        return req.getHeader("Authorization");
//    }
}
