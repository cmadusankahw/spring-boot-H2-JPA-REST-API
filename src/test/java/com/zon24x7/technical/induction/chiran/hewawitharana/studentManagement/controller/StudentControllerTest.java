package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.controller;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.AbstractTest;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.common.MessageConstants;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Date;

import static org.junit.Assert.*;

public class StudentControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllStudents() throws Exception {
        String uri = "/api/students";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student[] studentList = super.mapFromJson(content, Student[].class);
        assertTrue( studentList.length > 0);
    }

    @Test
    public void getStudentById() throws Exception {
        String uri = "/api/students/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student student = super.mapFromJson(content, Student.class);
        assertEquals(1, (long) student.getId());
        assertEquals("ravindu", student.getName());
        assertEquals("colombo", student.getAddress());
    }

    @Test
    public void getStudentsByName() throws Exception {
        String uri = "/api/student/ra";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student[] students = super.mapFromJson(content, Student[].class);
        assertEquals(1, (long) students[0].getId());
        assertEquals("ravindu", students[0].getName());
        assertEquals("colombo", students[0].getAddress());
    }


    @Test
    public void uploadFile() throws Exception {
        MockMultipartFile csvFile = new MockMultipartFile("file", "students.csv", "text/csv", "students".getBytes());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.multipart("/api/students/import")
                .file(csvFile))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        MockHttpServletResponse content = mvcResult.getResponse();
        assertEquals(content.getContentAsString(), MessageConstants.csvUploaded + csvFile.getOriginalFilename());
    }

    @Test
    public void createStudent() throws Exception {
        String uri = "/api/students";
        Student student = new Student("TestStudent",new Date(),"test address");

        String inputJson = super.mapToJson(student);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        MockHttpServletResponse content = mvcResult.getResponse();
        assertEquals(content.getContentType(), "application/json");
        assertEquals(content.getContentAsString().substring(0,3), inputJson.substring(0,3) );
    }

    @Test
    public void updateStudent() throws Exception {
        String uri = "/api/students/3";
        Student student = new Student("TestStudent",new Date(),"updated address");
        String inputJson = super.mapToJson(student);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        MockHttpServletResponse content = mvcResult.getResponse();
        assertEquals(content.getContentType(), "application/json");
        assertEquals(content.getContentAsString().substring(0,6), inputJson.substring(0,6) );
    }

    @Test
    public void deleteStudent() throws Exception {
        String uri = "/api/students/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, MessageConstants.studentDeleted + "1");
    }
}