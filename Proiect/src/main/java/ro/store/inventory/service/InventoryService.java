package ro.store.inventory.service;

import ro.store.inventory.model.*;
import ro.store.inventory.repository.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main Service class managing the core business logic of the Store Inventory.
 */
public class InventoryService {

    // --- In-Memory Collections ---
    // 1. Map for fast O(1) retrieval by Product ID
    private final Map<Integer, AbstractProduct> productCache = new HashMap<>();

    // 2. TreeSet for automatic sorting of products by price (requires Comparable implementation)
    private final Set<AbstractProduct> sortedProducts = new TreeSet<>();

    // 3. List to store order history
    private final List<Order> orderHistory = new ArrayList<>();

    // --- Singleton Repositories & Services ---
    private final CategoryRepository categoryRepo = CategoryRepository.getInstance();
    private final DistributorRepository distributorRepo = DistributorRepository.getInstance();
    private final UserRepository userRepo = UserRepository.getInstance();
    private final AuditService auditService = AuditService.getInstance();

    // Counter for generating sequential Order IDs
    private int orderIdCounter = 1;

    public InventoryService() {
        System.out.println("InventoryService initialized.");
    }

    // =========================================================================
    // ACTION 1: Add a new Product
    // =========================================================================
    public void addProduct(AbstractProduct product) {
        productCache.put(product.getId(), product);
        sortedProducts.add(product);
        auditService.logAction("ADD_PRODUCT");
        System.out.println("Added: " + product.getName());
    }

    // =========================================================================
    // ACTION 2: Remove a Product by ID
    // =========================================================================
    public void removeProduct(int productId) {
        AbstractProduct product = productCache.remove(productId);
        if (product != null) {
            sortedProducts.remove(product);
            auditService.logAction("REMOVE_PRODUCT");
            System.out.println("Removed product ID: " + productId);
        } else {
            System.err.println("Product not found!");
        }
    }

    // =========================================================================
    // ACTION 3: Get all products sorted by price (Ascending)
    // =========================================================================
    public List<AbstractProduct> getAllProductsSortedByPrice() {
        auditService.logAction("VIEW_SORTED_PRODUCTS");
        // The TreeSet is already sorted, we just return it as a List for easier iteration
        return new ArrayList<>(sortedProducts);
    }

    // =========================================================================
    // ACTION 4: Filter products by Category using Java Streams
    // =========================================================================
    public List<AbstractProduct> filterProductsByCategory(Category category) {
        auditService.logAction("FILTER_BY_CATEGORY");
        return productCache.values().stream()
                .filter(p -> p.getCategory().getId() == category.getId())
                .collect(Collectors.toList());
    }

    // =========================================================================
    // ACTION 5: Update Product Stock Quantity
    // =========================================================================
    public void updateProductStock(int productId, int newQuantity) {
        AbstractProduct product = productCache.get(productId);
        if (product != null) {
            product.setStockQuantity(newQuantity);
            auditService.logAction("UPDATE_STOCK");
            System.out.println("Updated stock for " + product.getName() + " to " + newQuantity);
        }
    }

    // =========================================================================
    // ACTION 6: Add a Distributor (Persisted to Database)
    // =========================================================================
    public void addDistributor(Distributor distributor) {
        distributorRepo.create(distributor);
        auditService.logAction("ADD_DISTRIBUTOR");
        System.out.println("Distributor saved to DB: " + distributor.getCompanyName());
    }

    // =========================================================================
    // ACTION 7: Add a Category (Persisted to Database)
    // =========================================================================
    public void addCategory(Category category) {
        categoryRepo.create(category);
        auditService.logAction("ADD_CATEGORY");
        System.out.println("Category saved to DB: " + category.getName());
    }

    // =========================================================================
    // ACTION 8: Register a New User (Persisted to Database)
    // =========================================================================
    public void registerUser(User user) {
        userRepo.create(user);
        auditService.logAction("REGISTER_USER");
        System.out.println("User saved to DB: " + user.getUsername());
    }

    // =========================================================================
    // ACTION 9: Place an Order (Complex Business Logic)
    // =========================================================================
    public void placeOrder(User user, Store store, List<Integer> productIds) {
        List<AbstractProduct> productsToOrder = new ArrayList<>();

        for (Integer id : productIds) {
            AbstractProduct p = productCache.get(id);
            if (p != null && p.getStockQuantity() > 0) {
                productsToOrder.add(p);
                // Decrease stock by 1 for this transaction
                p.setStockQuantity(p.getStockQuantity() - 1);
            } else {
                System.err.println("Product ID " + id + " is out of stock or does not exist. Skipping.");
            }
        }

        if (!productsToOrder.isEmpty()) {
            Order order = new Order(orderIdCounter++, user, store, productsToOrder, LocalDateTime.now());
            orderHistory.add(order);
            auditService.logAction("PLACE_ORDER");
            System.out.println("Order placed successfully! Total: $" + order.calculateTotal());
        } else {
            System.err.println("Order failed: No valid products to order.");
        }
    }

    // =========================================================================
    // ACTION 10: Get Order History for a specific User
    // =========================================================================
    public List<Order> getOrdersByUser(User user) {
        auditService.logAction("VIEW_USER_ORDERS");
        return orderHistory.stream()
                .filter(order -> order.getUser().getId() == user.getId())
                .collect(Collectors.toList());
    }
}