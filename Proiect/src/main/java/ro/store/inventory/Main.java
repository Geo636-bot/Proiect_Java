package ro.store.inventory;

import ro.store.inventory.model.*;
import ro.store.inventory.service.InventoryService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * The entry point of the Store Inventory Management Application.
 * Demonstrates the 10 core business actions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Store Inventory Management System ===\n");

        // Initialize the central Facade service
        InventoryService inventoryService = new InventoryService();

        // ---------------------------------------------------------
        // SETUP: Create base entities required for products & orders
        // ---------------------------------------------------------
        Category foodCategory = new Category(1, "Food", "Perishable food items");
        Category electronicsCategory = new Category(2, "Electronics", "Devices and appliances");

        Distributor freshFarms = new Distributor(1, "Fresh Farms SRL", "contact@freshfarms.ro");
        Distributor techSupply = new Distributor(2, "Tech Global", "sales@techglobal.com");

        User cashier = new User(101, "alex_cashier", "CASHIER");
        Store mainStore = new Store(1, "SuperStore Downtown", "123 Main St, Bucharest");

        System.out.println("\n--- Executing Stage 2 Actions (Database Persistence) ---");

        // ACTION 6: Add a Distributor
        inventoryService.addDistributor(freshFarms);
        inventoryService.addDistributor(techSupply);

        // ACTION 7: Add a Category
        inventoryService.addCategory(foodCategory);
        inventoryService.addCategory(electronicsCategory);

        // ACTION 8: Register a User
        inventoryService.registerUser(cashier);

        System.out.println("\n--- Executing Stage 1 Actions (In-Memory & Collections) ---");

        // Create some products utilizing Inheritance
        AbstractProduct milk = new PerishableProduct(
                1001, "Organic Milk", 1.99, 50, foodCategory, freshFarms, LocalDate.now().plusDays(7)
        );
        AbstractProduct laptop = new ElectronicsProduct(
                1002, "Pro Laptop 15", 1299.99, 10, electronicsCategory, techSupply, 24
        );
        AbstractProduct bread = new PerishableProduct(
                1003, "Whole Wheat Bread", 2.49, 30, foodCategory, freshFarms, LocalDate.now().plusDays(3)
        );

        // ACTION 1: Add Products
        inventoryService.addProduct(milk);
        inventoryService.addProduct(laptop);
        inventoryService.addProduct(bread);

        // ACTION 2: Remove a Product
        inventoryService.removeProduct(1003); // Removes the bread

        // ACTION 3: View Sorted Products (Triggering TreeSet's Comparable logic)
        System.out.println("\n--- Products Sorted by Price (Ascending) ---");
        List<AbstractProduct> sortedProducts = inventoryService.getAllProductsSortedByPrice();
        for (AbstractProduct p : sortedProducts) {
            System.out.println(p);
        }

        // ACTION 4: Filter Products by Category (Using Streams)
        System.out.println("\n--- Filtered Products: Electronics ---");
        List<AbstractProduct> techProducts = inventoryService.filterProductsByCategory(electronicsCategory);
        techProducts.forEach(System.out::println);

        // ACTION 5: Update Stock
        System.out.println("\n--- Updating Stock ---");
        inventoryService.updateProductStock(1001, 150); // Milk stock increases to 150

        // ACTION 9: Place an Order
        System.out.println("\n--- Placing an Order ---");
        // Customer buys 1 Milk (1001) and 1 Laptop (1002)
        List<Integer> productsToBuy = Arrays.asList(1001, 1002);
        inventoryService.placeOrder(cashier, mainStore, productsToBuy);

        // Verify stock decreased after order
        System.out.println("Milk Stock after order: " + milk.getStockQuantity());
        System.out.println("Laptop Stock after order: " + laptop.getStockQuantity());

        // ACTION 10: View User Orders
        System.out.println("\n--- Order History for User: " + cashier.getUsername() + " ---");
        List<Order> alexOrders = inventoryService.getOrdersByUser(cashier);
        for (Order order : alexOrders) {
            System.out.println(order);
            System.out.println("Items in order: ");
            order.getOrderedProducts().forEach(p -> System.out.println(" - " + p.getName()));
        }

        System.out.println("\n=== Application execution finished successfully. Check audit_log.csv! ===");
    }
}