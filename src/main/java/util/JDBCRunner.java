package main.java.util;

import org.postgresql.Driver;
import main.java.util.ConnectionManager;
import java.sql.SQLException;

public class JDBCRunner {
    public static void main(String[] args) throws SQLException {
        Class<Driver> driverClass = Driver.class;
        try (var connection = ConnectionManager.open()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }
}
