package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    Student mockStudent = new Student((long) 1, "Test Student", new Date(), "colombo 01");

    // String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

    @Test
    void uploadFile() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void getStudentById() {
    }

    @Test
    void getStudentsByName() {
    }

    @Test
    void createStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }
}