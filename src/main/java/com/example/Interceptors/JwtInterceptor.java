package com.example.Interceptors;
import com.example.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Autowired
    public JwtInterceptor(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }


    private static final String[] AllowApi = {"^/login$", "^/login?error", "^/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");

        if (AllowApiInterceptor(request)) return true;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

            response.getWriter().write("error invaild token");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);

            return false;
        }

        if (authorizationHeader.startsWith("Bearer ")) {

            return jwtProvider.CheckToken(authorizationHeader);
//
//            response.getWriter().write(" Unauthorize");
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(401);
        }

        return true;

    }

    private boolean AllowApiInterceptor(HttpServletRequest request) {

        for (String allowApi : AllowApi) {

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

