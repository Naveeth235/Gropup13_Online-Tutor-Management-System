package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/teacherDashboard")
    public String teacherDashboard() {
        return "teacherDashboard"; 
    }

    @GetMapping("/teacherclasses")
    public String teacherclass() {
        return "teacherclasses"; 
    }

}