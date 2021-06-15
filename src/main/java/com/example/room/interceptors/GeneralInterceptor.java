package com.example.room.interceptors;

import com.example.room.services.JwtService;
import com.example.room.services.MyUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class GeneralInterceptor implements HandlerInterceptor {
    private final MyUserDetailsService myUserDetailsService;
    private final JwtService jwtService;

    public GeneralInterceptor(MyUserDetailsService myUserDetailsService, JwtService jwtService) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtService = jwtService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
         String jwt = null;
         Boolean isTokenValid = false;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtService.extractUsername(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

            if(jwtService.validateToken(jwt,userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               isTokenValid = true;
            }


        }
        System.out.println("pre autho");

        return true;

     }
     @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

       System.out.println("Post Handle method is Calling");

     }

     @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) throws Exception{
         System.out.println("Request and Response is completed");

     }
}
