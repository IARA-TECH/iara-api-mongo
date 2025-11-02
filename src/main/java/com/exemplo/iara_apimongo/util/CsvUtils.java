package com.exemplo.iara_apimongo.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static ByteArrayInputStream convertToExcel(List<List<String>> values) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            for (int i = 0; i < values.size(); i++) {
                Row row = sheet.createRow(i);
                List<String> rowData = values.get(i);
                for (int j = 0; j < rowData.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowData.get(j));
                }
            }

            if (!values.isEmpty()) {
                for (int i = 0; i < values.get(0).size(); i++) {
                    sheet.autoSizeColumn(i);
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error converting CSV to Excel: " + e.getMessage(), e);
        }
    }

    public static List<List<String>> parseCsvToValues(MultipartFile csvFile) {
        List<List<String>> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8))) {
            String firstLine = br.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()) return result;

            String separator = firstLine.contains(";") ? ";" : ",";
            result.add(parseLine(firstLine, separator));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                result.add(parseLine(line, separator));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing CSV: " + e.getMessage(), e);
        }
        return result;
    }

    private static List<String> parseLine(String line, String separator) {
        String[] parts = line.split(separator, -1);
        List<String> row = new ArrayList<>();
        for (String part : parts) row.add(part.trim());
        return row;
    }
}
