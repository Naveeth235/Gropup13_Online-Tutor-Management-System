package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PendingEnrollment;

@Repository
public interface PendingEnrollmentRepository extends JpaRepository<PendingEnrollment, Long> {
    List<PendingEnrollment> findByStatus(String status);
}


