package ro.store.inventory.repository;

import ro.store.inventory.config.DatabaseConnection;
import ro.store.inventory.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRepository<User> {
    private static UserRepository instance;
    private final Connection connection;

    private UserRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    @Override
    public void create(User entity) {
        String query = "INSERT INTO users (id, username, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getUsername());
            stmt.setString(3, entity.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> read(int id) {
        // Omitted full implementation for brevity, follows same pattern as Category
        return Optional.empty();
    }

    @Override
    public List<User> readAll() { return new ArrayList<>(); }

    @Override
    public void update(User entity) { /* Follows same pattern */ }

    @Override
    public void delete(int id) { /* Follows same pattern */ }
}