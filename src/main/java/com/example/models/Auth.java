package com.example.models;



import org.hibernate.annotations.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
// khai báo annotation này có hiệu lực với method

public @interface Auth {
    // ở đây mình set cứng gồm 1 số quyền sau, chúng ta có thể thay thế sang
    // String hoặc int để sử dụng kèm db
    public enum Role {
        LOGIN, ADMIN, EMPLOYEE
    };

    // khai báo properties cho anotation với giá trị mặc định là LOGIN
    // ví dụ: @Auth(role = Role.LOGIN)
    public Role role() default Role.LOGIN; // default = @Auth()
}