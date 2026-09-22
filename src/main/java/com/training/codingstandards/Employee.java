package com.training.codingstandards;

import java.util.Objects;

/**
 * Employee record loaded from CSV.
 * Intentionally poorly encapsulated for the workshop.
 */
public class Employee {

    String empId;
    String name;
    String email;
    String department;
    double salary;
    int yearsOfService;
    String country;
    String managerEmail;

    public Employee() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) obj;
        return Objects.equals(empId, other.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }
}
