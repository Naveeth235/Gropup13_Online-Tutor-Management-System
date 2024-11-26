package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.EnrollmentService;

@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/student/enroll/request")
    public String requestEnrollment(@RequestParam Long courseId, Principal principal) {
        
        String email = principal.getName();

        return enrollmentService.savePendingEnrollment(email, courseId);
    }
    
}
