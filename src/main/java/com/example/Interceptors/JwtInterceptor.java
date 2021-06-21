package com.example.Interceptors;

import com.example.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProvider jwtProvider;
    // allow api , bieu thuc chinh quy
    private static final String[] AllowApi = {"^/login$", "^/login?error", "^/booking", "^/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // lay tu header cai dong authorization, duoi dang Bearer + token
        String authorizationHeader = request.getHeader("Authorization");

        // check api to allow
        if (AllowApiInterceptor(request)) return true;

        // check if authorizeHeader invaild, throw a exception
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // tra ve error invaild token
            response.getWriter().write("error invaild token");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            return false;
        }

        // checking client access token
        if (authorizationHeader.startsWith("Bearer ")) {
            // check token
            if (jwtProvider.CheckToken(authorizationHeader)) {
                return true;
            }
            // sai token tra ve loi
            response.getWriter().write(" Unauthorize");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);

            return false;
        }

        return true;
    }

    private boolean AllowApiInterceptor(HttpServletRequest request) {

        for (String allowApi : AllowApi) {
        // dung lop Panttern bieu thuc chinh quy Regex, neu url trung thi tra ve true
            Pattern pattern = Pattern.compile(allowApi);
            String url = request.getRequestURI();
            boolean matches = pattern
                    .matcher(url)
                    .matches();

            if (matches) {
                return true;
            }
        }

        return false;
    }

}
