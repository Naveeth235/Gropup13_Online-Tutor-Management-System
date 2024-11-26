package com.example.demo.Controller;

import com.example.demo.entity.Classes;
import com.example.demo.entity.User;
import com.example.demo.service.ClassService;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassesController {

    private final ClassService classService;
    private final UserService userService;

    public ClassesController(ClassService classService, UserService userService) {
        this.classService = classService;
        this.userService = userService;
    }

    @GetMapping("/teacher/addclass")
    @PreAuthorize("hasRole('TEACHER')")
    public String showAddClassForm(Model model) {
        return "addclass";
    }

    @PostMapping("/teacher/addclass")
    @PreAuthorize("hasRole('TEACHER')")
    public String addClass(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("fee") int fee,
                           @RequestParam("duration") Long duration) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User teacher = userService.findByEmail(email);

        if (teacher == null) {
            throw new RuntimeException("Teacher not found!");
        }

        Classes newClass = new Classes();
        newClass.setName(name);
        newClass.setDescription(description);
        newClass.setFee(fee);
        newClass.setDuration(duration);
        newClass.setT_id(teacher.getId());

        classService.addClass(newClass);

        return "teacherDashboard";
    }
}