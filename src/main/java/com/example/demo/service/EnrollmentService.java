package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.Classes; 
import com.example.demo.entity.PendingEnrollment;
import com.example.demo.repository.ClassRepository;  
import com.example.demo.repository.PendingEnrollmentRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

    @Autowired
    private PendingEnrollmentRepository pendingEnrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository; 

    public Long findStudentIdByEmail(String email) {
 
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public String savePendingEnrollment(String email, Long classId) {
         User student = userRepository.findByEmailIgnoreCase(email).orElse(null);
        if (student == null) {
            logger.error("User not found with email: {}", email);
            return "User not found with email: " + email;
        }

        Classes classEntity = classRepository.findById(classId).orElse(null);
        if (classEntity == null) {
            logger.error("Class not found with ID: {}", classId);
            return "Class not found with ID: " + classId;
        }

        PendingEnrollment pending = new PendingEnrollment();
        pending.setClasses(classEntity); 
        pending.setStudent(student);
        pending.setStatus("PENDING");

        logger.info("Saving pending enrollment: Student: {}, Class: {}", student.getId(), classEntity.getId());

        pendingEnrollmentRepository.save(pending);
        return "Enrollment request submitted successfully for class: " + classEntity.getName();
    }

    public List<PendingEnrollment> getAllPendingEnrollments() {
        return pendingEnrollmentRepository.findByStatus("PENDING");
    }
    
}
