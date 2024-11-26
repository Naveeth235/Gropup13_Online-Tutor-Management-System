package com.example.demo.service;

import com.example.demo.entity.StudentClass;
import com.example.demo.entity.User;
import com.example.demo.entity.Classes;
import com.example.demo.repository.StudentClassRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentClassEnrollService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    // Enroll student in the class
    public void enrollStudentInClass(Long classId) {
        // Fetch authenticated student's details
        String email = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepository.findByEmail(email);

        if (student == null) {
            throw new RuntimeException("Student not found!");
        }

        // Fetch the class details
        Classes course = classRepository.findById(classId).orElseThrow(() -> new RuntimeException("Class not found!"));

        // Create new StudentClass enrollment entry
        StudentClass studentClass = new StudentClass();
        studentClass.setStudentId(student.getId());
        studentClass.setClassId(classId);
        studentClass.setTeacherId(course.getT_id()); // Set the teacher's ID from the class

        // Save the enrollment record
        studentClassRepository.save(studentClass);
    }
}