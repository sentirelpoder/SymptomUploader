package com.gerimedica.ontimecoding.util;

import com.gerimedica.ontimecoding.exception.APIException;
import com.gerimedica.ontimecoding.model.Symptom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SymptomBuilder {

    private static final String FILE_TYPE = "text/csv";

    public static List<Symptom> readSymptomsFromFile(MultipartFile file) {
        if (!SymptomBuilder.isCSV(file))
            throw new APIException("File format must be CSV");
        List<Symptom> symptomList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (CSVRecord csvRecord : csvRecords) {
                symptomList.add(Symptom.builder()
                        .id(UUID.randomUUID().toString())
                        .source(csvRecord.get("source"))
                        .codeListCode(csvRecord.get("codeListCode"))
                        .code(csvRecord.get("code"))
                        .displayValue(csvRecord.get("displayValue"))
                        .longDescription(csvRecord.get("longDescription"))
                        .fromDate(StringUtils.hasLength(csvRecord.get("fromDate"))? LocalDate.parse(csvRecord.get("fromDate"), formatter) : null)
                        .toDate(StringUtils.hasLength(csvRecord.get("toDate")) ? LocalDate.parse(csvRecord.get("toDate")) : null)
                        .sortingPriority(StringUtils.hasLength(csvRecord.get("sortingPriority")) ? Integer.valueOf(csvRecord.get("sortingPriority")): null)
                        .build());
            }

        } catch (IOException e) {
            throw new APIException("Encountered a problem while reading symptoms from file",e);
        }

        return symptomList;
    }

    public static boolean isCSV(MultipartFile file) {
        return FILE_TYPE.equals(file.getContentType());
    }
}
