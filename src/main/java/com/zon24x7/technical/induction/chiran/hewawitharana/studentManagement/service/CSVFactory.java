package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.TableEntity;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Teacher;
import org.apache.commons.csv.CSVRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.CSVTypes.STUDENT;
import static com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.CSVTypes.TEACHER;

public class CSVFactory {

    private final CSVRecord csvRecord;
    private final Enum tableType;

    public CSVFactory(Enum tableType, CSVRecord csvRecord) {
        this.tableType = tableType;
        this.csvRecord = csvRecord;
    }

    public TableEntity create() {
        TableEntity tableEntity = null;

        try{
            if(STUDENT.equals(tableType)) {
                tableEntity = new Student(
                        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("name"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((csvRecord.get("dob"))),
                        csvRecord.get("address"));

            } else if(TEACHER.equals(tableType)) {
                tableEntity = new Teacher(
                        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("name"),
                        new SimpleDateFormat("yyyy-MM-dd").parse((csvRecord.get("dob"))),
                        csvRecord.get("address"));
            }
        }
        catch (ParseException e) {
            throw new RuntimeException("unable to create TableObject in requested type" + e.getMessage());
        }

        return tableEntity;
    }
}
