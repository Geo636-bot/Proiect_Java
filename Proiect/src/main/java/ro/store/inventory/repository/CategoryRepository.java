package ro.store.inventory.repository;

import ro.store.inventory.config.DatabaseConnection;
import ro.store.inventory.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepository implements CrudRepository<Category> {
    private static CategoryRepository instance;
    private final Connection connection;

    private CategoryRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }

    @Override
    public void create(Category entity) {
        String query = "INSERT INTO categories (id, name, description) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating category: " + e.getMessage());
        }
    }

    @Override
    public Optional<Category> read(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println("Error reading category: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Category> readAll() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println("Error reading all categories: " + e.getMessage());
        }
        return categories;
    }

    @Override
    public void update(Category entity) {
        String query = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating category: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting category: " + e.getMessage());
        }
    }
}