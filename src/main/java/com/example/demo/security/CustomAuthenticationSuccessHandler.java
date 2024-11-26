package com.example.demo.security;

import com.example.demo.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        String redirectUrl = determineTargetUrl(authentication);

        response.sendRedirect(redirectUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(Role.ROLE_ADMIN.name())); 
        boolean isStudent = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(Role.ROLE_STUDENT.name())); 
        boolean isTeacher = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(Role.ROLE_TEACHER.name())); 
        boolean isFinance = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(Role.ROLE_FINANCE.name())); 

                authentication.getAuthorities().forEach(auth -> 
                System.out.println("Granted Authority: " + auth.getAuthority()));
            

        if (isAdmin) {
            return "/admin";
        } else if (isStudent) {
            return "/student";
        } else if (isTeacher) {
            return "/teacher";
        } else if (isFinance) {
            return "/finance";
        } else {
            return "/index";
        }
    }
}
