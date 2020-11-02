package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.MessageConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.exception.ResourceNotFoundException;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindStudentByIdService {
    @Autowired
    private StudentRepository studentRepository;

    public Student findStudentById(Long studentId) throws ResourceNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.studentNotFound + studentId));
    }
}
