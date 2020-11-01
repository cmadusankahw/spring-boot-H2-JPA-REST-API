package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.StudentRepository;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.CSVTypes.STUDENT;
import static com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.CSVTypes.TEACHER;

@Service
public class CSVService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public void importCSV(MultipartFile file, Enum tableType) {
        if (CSVImporter.hasCSVFormat(file)) {
            try {
                List entities = CSVImporter.csvTotable(file.getInputStream(),tableType);
                if (STUDENT.equals(tableType)) {
                    studentRepository.saveAll(entities);
                } else if (TEACHER.equals(tableType)) {
                    teacherRepository.saveAll(entities);
                }

            } catch (IOException e) {
                throw new RuntimeException("fail to store csv data: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("please upload a csv file! ");
        }
    }
}
