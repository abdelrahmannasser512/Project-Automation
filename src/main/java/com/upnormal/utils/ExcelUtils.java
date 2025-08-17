package com.upnormal.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(" Failed to load Excel file: " + filePath, e);
        }
    }

    public Object[][] getData() {
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols]; // skip header

        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell == null) ? "" : cell.toString();
            }
        }

        return data;
    }
}
