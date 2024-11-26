package com.example.demo.Controller;

import com.example.demo.entity.Classes;
import com.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ClassService classService;

    @GetMapping("/studentclasses")
    public String getClasses(Model model) {
        List<Classes> classes = classService.getAllClasses(); 
        model.addAttribute("classes", classes); 
        return "studentclasses"; 
    }

    
    @GetMapping("/student")
    public String getstudentDashboard() {
        return "studentDashboard";
    }

    @GetMapping("/studentDashboard")
    public String getstudentDashboard1() {
        return "studentDashboard";
    }

    @GetMapping("/studentbankInfo")
    public String showHelpPage() {
        return "studentbankInfo";
    }
    
}
