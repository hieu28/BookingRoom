package com.example.interceptors;

import com.example.models.Auth;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // trích xuất method tương ứng với request mapping trong controller
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // tìm trong method có khai báo anotation Auth?
        Auth roleAnnotation = AnnotationUtils.findAnnotation(method, Auth.class);
        // nếu có lấy ra thuộc tính role, không trả về null
        Auth.Role role = roleAnnotation != null ? roleAnnotation.role() : null;

        // lấy các thông tin đăng nhập từ session
        HttpSession session = request.getSession();
        boolean isLogined = session.getAttribute("isLogin") != null ? (Boolean) session
        .getAttribute("isLogin") : false;
        Auth.Role loginRole = session.getAttribute("role") != null ? (Auth.Role) session
                .getAttribute("role") : null;


        // - trường hợp role yêu cầu của method = null bỏ qua interceptor này và
        // chạy bình thường
        // - khác null tức request này chỉ được thực hiên khi đã đăng nhập
        if (role != null){
            // chưa đăng nhập chuyển hướng sang trang login để đăng nhập
            if (!isLogined){
                response.sendRedirect("login");
                return false;
            }else{
                // - trường hợp đã login tiến hành kiểm tra role
                // - những trường hợp chỉ yêu cầu login mà không yêu cầu cụ thể
                // role nào thì tất cả các role đều có quyền truy cập
                // - trường hợp yêu cầu cụ thể loại role sau khi đăng nhập thì
                // phải kiểm tra
                // - không thoả mãn điều kiện dưới chuyển hướng sang trang
                // denied
                if (role != Auth.Role.LOGIN && role != loginRole){
                    response.sendRedirect("deny?url=\"" + request.getRequestURI().toString()
                            +"?" + request.getQueryString() + "\"&role=" +role);
                    return false;
                }
            }
        }

        return true;
        //return super.preHandle(request, response, handler);
    }
}
