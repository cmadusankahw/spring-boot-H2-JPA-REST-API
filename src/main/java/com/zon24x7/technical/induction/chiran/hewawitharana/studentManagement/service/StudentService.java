package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.MessageConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.exception.ResourceNotFoundException;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student findStudentById(Long studentId) throws ResourceNotFoundException {
        return studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.studentNotFound + studentId));
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.studentNotFound + studentId));

        student.setName(studentDetails.getName());
        student.setAddress(studentDetails.getAddress());
        student.setDob(student.getDob());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.studentNotFound + studentId));
        studentRepository.delete(student);
    }

}
