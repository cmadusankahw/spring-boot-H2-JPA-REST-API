package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.controller;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.CommonConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.MessageConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.exception.ResourceNotFoundException;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.message.ResponseMessage;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service.CSVService;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.CSVTypes.STUDENT;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private CSVService csvService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.findAllStudents();
            return ResponseEntity.ok().body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)  {
        try {
            Student student = studentService.findStudentById(studentId);
            return ResponseEntity.ok().body(student);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/student/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable(value = CommonConstants.name) String studentName) {
        try {
            List<Student> students = studentService.findStudentsByName(studentName);
            return ResponseEntity.ok().body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @PostMapping("/students/import")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(CommonConstants.file) MultipartFile file) {
        try {
            csvService.importCSV(file, STUDENT);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(MessageConstants.csvUploaded + file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(MessageConstants.csvNotUploaded + file.getOriginalFilename()));
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return ResponseEntity.ok().body(student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity <Student> updateStudent(@PathVariable(value = CommonConstants.id) Long studentId,
                                                    @Valid @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.updateStudent(studentId, studentDetails);
            return ResponseEntity.ok().body(updatedStudent);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable(value = CommonConstants.id) Long studentId) {
        try {
           studentService.deleteStudent(studentId);
            return ResponseEntity.ok().body(new ResponseMessage(MessageConstants.studentDeleted + studentId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(MessageConstants.studentNotDeleted + studentId));
        }
    }
}
