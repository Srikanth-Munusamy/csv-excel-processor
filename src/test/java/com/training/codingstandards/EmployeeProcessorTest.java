package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeProcessorTest {

    @Test
    void processCreatesPayrollRowForEachEmployee() {
        Employee employee = new Employee();
        employee.empId = "1001";
        employee.name = "Asha Raman";
        employee.email = "asha.raman@example.com";
        employee.department = "Engineering";
        employee.salary = 92000;
        employee.yearsOfService = 6;
        employee.country = "IN";
        employee.managerEmail = "lead.eng@example.com";

        EmployeeProcessor processor = new EmployeeProcessor();
        List<EmployeeProcessor.PayrollRow> rows = processor.process(Arrays.asList(employee));

        assertNotNull(rows);
        assertEquals(1, rows.size());
        assertEquals("1001", rows.get(0).empId);
        assertEquals("Engineering", rows.get(0).department);
    }

    @Test
    void processHandlesAllBundledEmployeeRules() {
        List<Employee> employees = new CsvEmployeeReader().read(null);

        List<EmployeeProcessor.PayrollRow> rows = new EmployeeProcessor().process(employees);

        assertEquals(8, rows.size());
        assertEquals(9200, rows.get(0).bonus);
        assertEquals("L5", rows.get(2).grade);
        assertEquals("L1", rows.get(7).grade);
    }

    @Test
    void processReturnsEmptyListForNullInput() {
        assertNotNull(new EmployeeProcessor().process(null));
        assertEquals(0, new EmployeeProcessor().process(null).size());
    }
}
