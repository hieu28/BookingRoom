package com.example.room.security;

import com.example.room.exceptions.JwtAuthenticationEntryPoint;
//import com.example.room.jwt.JwtRequestFilter;
import com.example.room.interceptors.GeneralInterceptor;
import com.example.room.interceptors.LoginInterceptor;
import com.example.room.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final UserDetailsService myUserDetailsService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfigurer(MyUserDetailsService myUserDetailsService, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.myUserDetailsService = myUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Bean
    protected BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
