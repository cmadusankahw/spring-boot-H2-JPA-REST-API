package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.MessageConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.exception.ResourceNotFoundException;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void deleteStudent(Long studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.studentNotFound + studentId));
        studentRepository.delete(student);
    }
}
