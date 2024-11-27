package com.example.demo.service;

import com.example.demo.entity.Classes;
import com.example.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository; 

    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }
    public ClassService(ClassRepository classesRepository) {
        this.classRepository = classesRepository;
    }

    public void addClass(Classes newClass) {
        classRepository.save(newClass);
    }

    public void deleteClassById(Long classId) {
        classRepository.deleteById(classId);
    }
}