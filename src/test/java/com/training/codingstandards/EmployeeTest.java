package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {

    @Test
    void employeesWithSameIdAreEqualAndHaveSameHashCode() {
        Employee first = new Employee();
        first.empId = new String("1001");
        Employee second = new Employee();
        second.empId = new String("1001");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, new Object());
    }
}