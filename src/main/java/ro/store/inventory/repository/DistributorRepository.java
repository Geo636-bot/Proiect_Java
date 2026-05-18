package ro.store.inventory.repository;

import ro.store.inventory.config.DatabaseConnection;
import ro.store.inventory.model.Distributor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DistributorRepository implements CrudRepository<Distributor> {
    private static DistributorRepository instance;
    private final Connection connection;

    private DistributorRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized DistributorRepository getInstance() {
        if (instance == null) {
            instance = new DistributorRepository();
        }
        return instance;
    }

    @Override
    public void create(Distributor entity) {
        String query = "INSERT INTO distributors (id, company_name, contact_email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getCompanyName());
            stmt.setString(3, entity.getContactEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating distributor: " + e.getMessage());
        }
    }

    @Override
    public Optional<Distributor> read(int id) { return Optional.empty(); }
    @Override
    public List<Distributor> readAll() { return new ArrayList<>(); }
    @Override
    public void update(Distributor entity) { }
    @Override
    public void delete(int id) { }
}