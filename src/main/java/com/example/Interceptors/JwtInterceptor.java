package com.example.Interceptors;
import com.example.exceptions.JwtNotFound;
import com.example.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String[] AllowApi = {"^/login$", "^/login?error", "^/error",
            "^/v2/api-docs$", "^/configuration/ui$", "^/swagger-resources$","^/swagger-resources/\\**$",
            "^/swagger-ui.html$", "^/webjars/\\**$","^/swagger-resources/configuration/ui$","^/swagger-ui.html$"};

    private final JwtProvider jwtProvider;
    @Autowired
    public JwtInterceptor(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String authorizationHeader = request.getHeader("Authorization");

        if (AllowApiInterceptor(request)) return true;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

             throw new JwtNotFound();
        }

        if (authorizationHeader.startsWith("Bearer ")) {

            return jwtProvider.CheckToken(authorizationHeader);
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

