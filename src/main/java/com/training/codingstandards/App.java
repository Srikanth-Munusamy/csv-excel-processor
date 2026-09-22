package com.training.codingstandards;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String csvPath = null;
        String excelPath = "payroll-report.xlsx";

        if (args.length > 0) {
            csvPath = args[0];
        }
        if (args.length > 1) {
            excelPath = args[1];
        }

        LOGGER.info("CSV to Excel processor starting...");
        CsvEmployeeReader reader = new CsvEmployeeReader();
        List<Employee> employees = reader.read(csvPath);

        EmployeeProcessor processor = new EmployeeProcessor();
        List<EmployeeProcessor.PayrollRow> rows = processor.process(employees);

        File out = new File(excelPath);
        ExcelReportWriter writer = new ExcelReportWriter();
        writer.write(rows, out.getAbsolutePath());

        DatabaseHelper db = new DatabaseHelper();
        if (args.length > 2) {
            db.auditExport(args[2]);
            if (!employees.isEmpty()) {
                Employee lookedUp = db.findEmployee(args.length > 3 ? args[3] : employees.get(0).empId);
                if (lookedUp != null) {
                    LOGGER.info(() -> "Lookup result: " + lookedUp.name);
                }
            }
        }

        LOGGER.info("Processed " + rows.size() + " employees into " + excelPath);
    }
}
