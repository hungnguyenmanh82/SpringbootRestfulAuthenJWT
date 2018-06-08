package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.example.demo.service.TokenAuthenticationService;
 
public class JWTAuthenticationFilter extends GenericFilterBean {
     
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
         
        System.out.println("JWTAuthenticationFilter.doFilter");
         
        //đây là filter 2 => sẽ tạo ra exception khi request ko thỏa mãn: http://localhost:8080/login?username=tom&password=123
        Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) servletRequest);
         
        SecurityContextHolder.getContext().setAuthentication(authentication);
         
        filterChain.doFilter(servletRequest, servletResponse);
    }
     
}
