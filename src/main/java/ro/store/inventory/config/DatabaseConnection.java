package ro.store.inventory.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to manage the JDBC Database Connection.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Database credentials (adjust according to your local setup)
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // Private constructor to prevent instantiation from outside
    private DatabaseConnection() {
        try {
            // Load the MySQL JDBC driver (optional in modern JDBC, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    /**
     * Retrieves the single instance of the DatabaseConnection.
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Exposes the raw Connection object for repositories to use.
     */
    public Connection getConnection() {
        return connection;
    }
}