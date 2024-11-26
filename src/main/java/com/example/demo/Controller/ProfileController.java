package com.example.demo.Controller;

import com.example.demo.entity.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserService userService;
    @SuppressWarnings("unused")
    private final CustomUserDetailsService customUserDetailsService;

    public ProfileController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/student/studentprofile")
    @PreAuthorize("hasRole('STUDENT')")
    public String viewProfileStudent(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); 

        User user = userService.findByEmail(email);

        if (user != null) {
            
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "studentprofile"; 
    }

    @GetMapping("/teacher/teacherprofile")
    @PreAuthorize("hasRole('TEACHER')")
    public String viewProfileTeacher(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findByEmail(email);

        if (user != null) {
        
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "teacherprofile";
    }

    @GetMapping("/admin/adminprofile")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewProfileAdmin(Model model) {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); 

        
        User user = userService.findByEmail(email);

        if (user != null) {
            
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "adminprofile"; 
    }

    @GetMapping("/finance/financeprofile")
    @PreAuthorize("hasRole('FINANCE')")
    public String viewProfileFinance(Model model) {
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findByEmail(email);

        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "financeprofile"; 
    }
}
