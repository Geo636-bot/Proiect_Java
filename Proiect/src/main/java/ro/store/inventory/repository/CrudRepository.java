package ro.store.inventory.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface defining standard CRUD operations.
 * @param <T> The entity type
 */
public interface CrudRepository<T> {
    void create(T entity);
    Optional<T> read(int id);
    List<T> readAll();
    void update(T entity);
    void delete(int id);
}