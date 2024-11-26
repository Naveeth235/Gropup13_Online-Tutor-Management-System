package com.example.demo.Controller;

import com.example.demo.service.StudentClassEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentClassEnrollController {

    @Autowired
    private StudentClassEnrollService studentClassEnrollService;

    // Endpoint to enroll the student in a class
    @PostMapping("/student/enroll")
    public String enrollStudent(@RequestParam("classId") Long classId) {
        studentClassEnrollService.enrollStudentInClass(classId);
        return "redirect:/student/studentclasses"; // Redirect back to the classes page
    }
}