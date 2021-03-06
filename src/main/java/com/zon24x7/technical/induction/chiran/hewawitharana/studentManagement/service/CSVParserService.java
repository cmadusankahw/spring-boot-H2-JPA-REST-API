package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.service;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.TableEntity;
import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.factory.CSVFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CSVParserService {
    public static String TYPE = "text/csv";
    // static String[] HEADERS = { "id", "name", "dob", "address" };

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<TableEntity> csvTotable(InputStream is, Enum tableType) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<TableEntity> tableEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                TableEntity entity = new CSVFactory(tableType, csvRecord).create();
                tableEntities.add(entity);
            }

            return tableEntities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
