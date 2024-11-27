package com.example.demo.Controller;

import com.example.demo.entity.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ProfileController {

    private final UserService userService;
    @SuppressWarnings("unused")
    private final CustomUserDetailsService customUserDetailsService;



    public ProfileController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName(); // Assuming email is the username
        return userService.findByEmail(email);
    }

    @GetMapping("/student/studentprofile")
    @PreAuthorize("hasRole('STUDENT')")
    public String viewProfileStudent(Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); 

        User user = userService.findByEmail(email);

        // Get currently logged in user id()
        System.out.println(user.getId());

        if (user != null) {
            
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "studentProfile";
    }

    @GetMapping("/student/studentprofile/edit")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMIN') or hasRole('FINANCE')")
    public String showEditProfileForm(Model model, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);

        if (currentUser != null) {
            model.addAttribute("user", currentUser);
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "editProfile"; // A Thymeleaf template for the edit profile page
    }

    @PutMapping("/student/studentprofile/edit")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/student/studentprofile/edit")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMIN') or hasRole('FINANCE')")
    public String updateProfile(@ModelAttribute("user") User updatedUser, Authentication authentication, Model model) {
        User currentUser = getCurrentUser(authentication);

        if (currentUser != null) {
            // Update editable fields only
            currentUser.setName(updatedUser.getName());
            currentUser.setPhone(updatedUser.getPhone());
            currentUser.setBio(updatedUser.getBio());
            // Add any additional fields you want to allow editing

            userService.updateUser(currentUser.getId(), currentUser);

            model.addAttribute("user", currentUser);
            model.addAttribute("success", "Profile updated successfully!");
        } else {
            model.addAttribute("error", "User not found!");
        }

        return "studentProfile"; // Redirect to the same page or a success page
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

        return "financeProfile";
    }
}
