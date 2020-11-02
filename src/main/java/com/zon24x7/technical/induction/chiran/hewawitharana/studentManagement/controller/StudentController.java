package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.controller;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.CommonConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.MessageConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.exception.ResourceNotFoundException;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.message.ResponseMessage;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service.*;
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
    private CSVImportService csvImportService;

    @Autowired
    private FindStudentByIdService findStudentByIdService;

    @Autowired
    private FindAllStudentsService findAllStudentsService;

    @Autowired
    private FindStudentByNameService findStudentByNameService;

    @Autowired
    private CreateStudentService createStudentService;

    @Autowired
    private UpdateStudentService updateStudentService;

    @Autowired
    private DeleteStudentService deleteStudentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = findAllStudentsService.findAllStudents();
            return ResponseEntity.ok().body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
            Student student = findStudentByIdService.findStudentById(studentId);
            return ResponseEntity.ok().body(student);
    }

    @GetMapping("/student/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable(value = CommonConstants.name) String studentName) throws Exception {
            List<Student> students = findStudentByNameService.findStudentsByName(studentName);
            return ResponseEntity.ok().body(students);
    }

    @PostMapping("/students/import")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(CommonConstants.file) MultipartFile file) throws Exception {
            csvImportService.importCSV(file, STUDENT);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(MessageConstants.csvUploaded + file.getOriginalFilename()));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) throws Exception {
            createStudentService.saveStudent(student);
            return ResponseEntity.ok().body(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity <Student> updateStudent(@PathVariable(value = CommonConstants.id) Long studentId,
                                                    @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
            Student updatedStudent = updateStudentService.updateStudent(studentId, studentDetails);
            return ResponseEntity.ok().body(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable(value = CommonConstants.id) Long studentId) throws ResourceNotFoundException {
           deleteStudentService.deleteStudent(studentId);
            return ResponseEntity.ok().body(new ResponseMessage(MessageConstants.studentDeleted + studentId));
    }
}
