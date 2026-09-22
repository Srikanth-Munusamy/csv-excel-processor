package com.training.codingstandards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class DatabaseHelper {

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/hr";
    private static final Logger LOGGER = Logger.getLogger(DatabaseHelper.class.getName());

    public Employee findEmployee(String empId) {
        String url = System.getenv().getOrDefault("DB_URL", DEFAULT_URL);
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        if (user == null || password == null) {
            return null;
        }
        String sql = "SELECT emp_id, name FROM employees WHERE emp_id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, empId);
            try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                Employee employee = new Employee();
                employee.empId = rs.getString("emp_id");
                employee.name = rs.getString("name");
                return employee;
            }
            }
        } catch (SQLException e) {
            LOGGER.warning("Employee lookup failed: " + e.getMessage());
            return null;
        }
        return null;
    }

    public void auditExport(String userInputPath) {
        if (userInputPath == null || userInputPath.isBlank()) {
            return;
        }
        Path path = Path.of(userInputPath).normalize();
        if (Files.exists(path)) {
            LOGGER.info(() -> "Export path is available: " + path);
        }
    }
}
