package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.controller;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.exception.ResourceNotFoundException;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.helper.CSVHelper;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.message.ResponseMessage;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.StudentRepository;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CSVService csvService;

    @PostMapping("/students/import")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
            try {
                csvService.importCSV(file);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Could not upload the file: " + file.getOriginalFilename() + "!"));
            }
    }


    // ToDo Add Mappings to separate Service Files and call here with Response Message
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity< Student > getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/student/{name}")
    public List<Student> getStudentsByName(@PathVariable(value = "name") String studentName) {
        return studentRepository.findByName(studentName);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
            return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity < Student > updateStudent(@PathVariable(value = "id") Long studentId,
                                                    @Valid @RequestBody Student studentDetails)
                                                    throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        student.setName(studentDetails.getName());
        student.setAddress(studentDetails.getAddress());
        student.setDob(studentDetails.getDob());
        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public Map< String, Boolean > deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentRepository.delete(student);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
