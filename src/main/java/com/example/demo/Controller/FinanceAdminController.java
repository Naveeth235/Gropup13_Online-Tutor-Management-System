package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PendingEnrollment;
import com.example.demo.service.EnrollmentService;

@RestController
@RequestMapping("/finance")
public class FinanceAdminController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/pendingpage")
    public String listPendingRequests(Model model) {
       
        List<PendingEnrollment> pendingRequests = enrollmentService.getAllPendingEnrollments();

        model.addAttribute("pendingRequests", pendingRequests);

        return "pendingpage";
    }
    
}
