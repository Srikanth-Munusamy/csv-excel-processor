package com.training.codingstandards;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelReportWriter {

    public void write(List<EmployeeProcessor.PayrollRow> rows, String outputPath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(ReportConfig.OUTPUT_SHEET);

        Row header = sheet.createRow(0);
        Cell c0 = header.createCell(0);
        c0.setCellValue("Employee Id");
        Cell c1 = header.createCell(1);
        c1.setCellValue("Name");
        Cell c2 = header.createCell(2);
        c2.setCellValue("Email");
        Cell c3 = header.createCell(3);
        c3.setCellValue("Department");
        Cell c4 = header.createCell(4);
        c4.setCellValue("Base Salary");
        Cell c5 = header.createCell(5);
        c5.setCellValue("Bonus");
        Cell c6 = header.createCell(6);
        c6.setCellValue("Tax");
        Cell c7 = header.createCell(7);
        c7.setCellValue("Net Pay");
        Cell c8 = header.createCell(8);
        c8.setCellValue("Grade");
        Cell c9 = header.createCell(9);
        c9.setCellValue("Hashed Id");
        Cell c10 = header.createCell(10);
        c10.setCellValue("Session Token");

        int rowIndex = 1;
        for (EmployeeProcessor.PayrollRow payrollRow : rows) {
            Row row = sheet.createRow(rowIndex);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(payrollRow.empId);
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(payrollRow.name);
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(payrollRow.email);
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(payrollRow.department);
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(payrollRow.baseSalary);
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(payrollRow.bonus);
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(payrollRow.tax);
            Cell cell7 = row.createCell(7);
            cell7.setCellValue(payrollRow.netPay);
            Cell cell8 = row.createCell(8);
            cell8.setCellValue(payrollRow.grade);
            Cell cell9 = row.createCell(9);
            cell9.setCellValue(payrollRow.hashedId);
            Cell cell10 = row.createCell(10);
            cell10.setCellValue(payrollRow.token);
            rowIndex = rowIndex + 1;
        }

            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                workbook.write(out);
            } catch (java.io.IOException e) {
                throw new IllegalStateException("Unable to write Excel report", e);
            }
        } catch (java.io.IOException e) {
            throw new IllegalStateException("Unable to create Excel workbook", e);
        }
    }
}
