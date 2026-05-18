# Store Inventory Management (Gestiune stocuri magazin)

A robust, modern Java application built to manage store inventories, process orders, and track user actions. This project was developed with a strict adherence to Object-Oriented Programming (OOP) principles, clean architecture, and standard Java design patterns.

## 🚀 Project Overview

The system is divided into two main stages of architecture:
1. **Core OOP & Memory Management:** Utilizes advanced Java Collections (Maps, Lists, TreeSets), Streams, Inheritance, and Encapsulation to manage entities in memory.
2. **Persistence & Auditing:** Integrates JDBC for a relational database connection (MySQL) to persist critical entities, alongside a file-based auditing system to track user actions.

## 🏗️ Architecture & Technologies

* **Language:** Java (JDK 8+)
* **Database:** MySQL
* **Database Connectivity:** JDBC (Java Database Connectivity)
* **Design Patterns Used:**
  * **Singleton:** Used for the Database Connection, CRUD Repositories, and the Audit Service to ensure only one instance exists.
  * **Facade (Service Layer):** The `InventoryService` acts as a central hub abstracting the complex interactions between in-memory collections and database persistence.

## ✨ Core Features & Actions

The central `InventoryService` supports 10 distinct business operations:
1. **Add Product:** Adds a product to the in-memory cache and sorted collection.
2. **Remove Product:** Safely removes a product from active tracking.
3. **View Sorted Products:** Returns all products automatically sorted by price (Ascending).
4. **Filter by Category:** Uses Java Streams to filter products by their assigned category.
5. **Update Stock:** Modifies the available quantity of a specific product.
6. **Add Distributor:** Persists a new distributor directly to the database.
7. **Add Category:** Persists a new product category directly to the database.
8. **Register User:** Persists a new system user to the database.
9. **Place Order:** Complex logic that checks stock, decreases inventory, calculates totals, and records the transaction.
10. **View User Orders:** Retrieves a history of all orders placed by a specific user.

## 📁 Detailed Project Structure

Below is the complete file tree of the application, mapping out all entities, repositories, and services:

```text
store-inventory-management/
├── src/
│   └── main/
│       └── java/
│           └── ro/
│               └── store/
│                   └── inventory/
│                       ├── config/
│                       │   └── DatabaseConnection.java       # Singleton JDBC setup
│                       ├── model/
│                       │   ├── AbstractProduct.java          # Base abstract class
│                       │   ├── Category.java                 # Entity
│                       │   ├── Distributor.java              # Entity
│                       │   ├── ElectronicsProduct.java       # Inherits AbstractProduct
│                       │   ├── Order.java                    # Complex Entity
│                       │   ├── PerishableProduct.java        # Inherits AbstractProduct
│                       │   ├── Store.java                    # Entity
│                       │   └── User.java                     # Entity
│                       ├── repository/
│                       │   ├── CategoryRepository.java       # JDBC CRUD Implementation
│                       │   ├── CrudRepository.java           # Generic Interface
│                       │   ├── DistributorRepository.java    # JDBC CRUD Implementation
│                       │   ├── StoreRepository.java          # JDBC CRUD Implementation
│                       │   └── UserRepository.java           # JDBC CRUD Implementation
│                       ├── service/
│                       │   ├── AuditService.java             # Singleton CSV Logger
│                       │   └── InventoryService.java         # Core Business Logic Facade
│                       └── Main.java                         # Application Entry Point
├── database/
│   └── init_db.sql                                           # MySQL table creation scripts
├── audit_log.csv                                             # Auto-generated audit log
└── README.md                                                 # Project documentation
