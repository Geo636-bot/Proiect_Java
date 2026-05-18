package ro.store.inventory.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton service responsible for logging application actions to a CSV file.
 */
public class AuditService {
    private static AuditService instance;
    private static final String FILE_PATH = "audit_log.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AuditService() {
        // Initialize the file with a header if it doesn't exist/is empty
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            // Uncomment the next line if you want the header in the file:
            writer.println("nume_actiune, timestamp");
        } catch (IOException e) {
            System.err.println("Failed to initialize audit file: " + e.getMessage());
        }
    }

    public static synchronized AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    /**
     * Logs an action to the CSV file.
     * @param actionName The name of the action performed (nume_actiune)
     */
    public void logAction(String actionName) {
        String timestamp = LocalDateTime.now().format(FORMATTER);

        // Try-with-resources ensures the writer is closed automatically
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)))) {
            writer.println(actionName + ", " + timestamp);
        } catch (IOException e) {
            System.err.println("Error writing to audit log: " + e.getMessage());
        }
    }
}