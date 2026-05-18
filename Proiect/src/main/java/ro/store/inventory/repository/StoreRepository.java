package ro.store.inventory.repository;

import ro.store.inventory.config.DatabaseConnection;
import ro.store.inventory.model.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreRepository implements CrudRepository<Store> {
    private static StoreRepository instance;
    private final Connection connection;

    private StoreRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized StoreRepository getInstance() {
        if (instance == null) {
            instance = new StoreRepository();
        }
        return instance;
    }

    @Override
    public void create(Store entity) {
        String query = "INSERT INTO stores (id, name, address) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating store: " + e.getMessage());
        }
    }

    @Override
    public Optional<Store> read(int id) { return Optional.empty(); }
    @Override
    public List<Store> readAll() { return new ArrayList<>(); }
    @Override
    public void update(Store entity) { }
    @Override
    public void delete(int id) { }
}