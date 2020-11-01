package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.helper;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    // static String[] HEADERS = { "id", "name", "dob", "address" };

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Student> csvToStudents(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            // ToDo use factory pattern to extend CSV creation
            List<Student> students = new ArrayList<Student>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Student Student = new Student(
                        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("name"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((csvRecord.get("dob"))),
                        csvRecord.get("address")
                );

                students.add(Student);
            }

            return students;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
