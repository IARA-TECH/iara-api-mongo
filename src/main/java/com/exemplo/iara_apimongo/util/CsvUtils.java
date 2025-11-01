package com.exemplo.iara_apimongo.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static ByteArrayInputStream convertToExcel(List<List<Integer>> values) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Sheet1");

            for (int i = 0; i < values.size(); i++) {
                Row row = sheet.createRow(i);
                List<Integer> rowData = values.get(i);
                for (int j = 0; j < rowData.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowData.get(j));
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static List<List<Integer>> parseCsvToValues(MultipartFile csvFile) throws IOException {
        List<List<Integer>> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                List<Integer> row = new ArrayList<>();
                for (String part : parts) {
                    row.add(Integer.parseInt(part.trim()));
                }
                result.add(row);
            }
        }
        return result;
    }
}
