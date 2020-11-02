package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllStudentsService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}
