# Store Inventory Management (Gestiune Stocuri Magazin)

A Java Object-Oriented Programming project that implements a complete **Store Inventory Management System** .
The project demonstrates core OOP principles, the Java Collections Framework, JDBC-based persistence, generic Singleton CRUD services, and a CSV-based audit logging mechanism.

---

## 📚 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Technologies](#-technologies)
- [Entities](#-entities)
- [Main Operations (10 Actions)](#-main-operations-10-actions)
- [Database Setup](#-database-setup)
- [How to Build & Run](#-how-to-build--run)
- [Audit Log](#-audit-log)
- [Academic Context](#-academic-context)
- [License](#-license)

---

## 🧭 Overview

This project simulates a **store inventory management platform** where products are organized by category, supplied by distributors, stored in physical stores, and ordered by users.
The system is built in **modern Java** and follows a clean, layered architecture:

1. **Model layer** – Entity classes with encapsulation and inheritance.
2. **Persistence layer** – JDBC connection and generic Singleton CRUD repositories.
3. **Service layer** – Business logic exposing at least 10 operations.
4. **Audit layer** – Singleton service writing every action into a CSV file.
5. **Application layer** – `Main` class that demonstrates all functionalities.

---

## ✨ Features

- ✅ At least **8 distinct entity classes** with full encapsulation.
- ✅ **Inheritance** hierarchy (`Product` → `PerishableProduct`, `ElectronicsProduct`).
- ✅ Use of **multiple Java Collections** (`List`, `Set`, `Map`), including a **sorted collection** (`TreeSet` / `Comparator`).
- ✅ **JDBC persistence** with a relational database.
- ✅ **Generic Singleton CRUD services** for at least 4 entities.
- ✅ **CSV audit logging** with format: `action_name, timestamp`.
- ✅ **10 distinct business operations** demonstrated from the `Main` class.
- ✅ Clean, modular, heavily commented code.

---

## 🏗 Architecture

```
┌────────────────────────────┐
│        Main (App)          │
└─────────────┬──────────────┘
              │
              ▼
┌────────────────────────────┐
│     Business Service       │  ← 10 actions
└─────────────┬──────────────┘
              │
   ┌──────────┴──────────┐
   ▼                     ▼
┌──────────────┐   ┌──────────────┐
│ Repositories │   │  Audit (CSV) │
│ (JDBC CRUD)  │   │  Singleton   │
└──────┬───────┘   └──────────────┘
       ▼
┌──────────────┐
│   Database   │
└──────────────┘
```

---

## 📁 Project Structure

```
store-inventory-management/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── gestiune/
│       │           └── store/
│       │               ├── model/
│       │               │   ├── enums/
│       │               │   │   ├── OrderStatus.java
│       │               │   │   └── UserRole.java
│       │               │   ├── Product.java
│       │               │   ├── PerishableProduct.java
│       │               │   ├── ElectronicsProduct.java
│       │               │   ├── Category.java
│       │               │   ├── Distributor.java
│       │               │   ├── Address.java
│       │               │   ├── Store.java
│       │               │   ├── User.java
│       │               │   ├── Order.java
│       │               │   └── OrderItem.java
│       │               ├── persistence/
│       │               │   ├── DatabaseConnection.java
│       │               │   ├── GenericRepository.java
│       │               │   ├── ProductRepository.java
│       │               │   ├── CategoryRepository.java
│       │               │   ├── DistributorRepository.java
│       │               │   └── UserRepository.java
│       │               ├── service/
│       │               │   └── InventoryService.java
│       │               ├── audit/
│       │               │   └── AuditService.java
│       │               └── app/
│       │                   └── Main.java
│       └── resources/
│           └── sql/
│               └── create_tables.sql
├── audit.csv          (generated at runtime)
└── README.md
```

---

## 🛠 Technologies

| Component       | Technology                       |
|-----------------|----------------------------------|
| Language        | Java 17+                         |
| Build Tool      | Plain `javac` / Maven (optional) |
| Database        | MySQL / PostgreSQL (JDBC)        |
| Persistence API | JDBC                             |
| Logging         | CSV file (custom)                |
| IDE             | IntelliJ IDEA / Eclipse / VS Code|

---

## 📦 Entities

The system contains at least **8 entity classes** :

1. **Product** *(abstract base class)*
2. **PerishableProduct** *(extends Product)*
3. **ElectronicsProduct** *(extends Product)*
4. **Category**
5. **Distributor**
6. **Address**
7. **Store**
8. **User**
9. **Order**
10. **OrderItem** 
All entities follow strict **encapsulation** : private/protected fields with public getters and setters.

---

## 🚀 Main Operations (10 Actions)

The business service exposes at least 10 operations, each logged in the audit file:

1. Add a new product
2. Remove a product by ID
3. Update product stock
4. Filter products by category
5. Sort products by price (ascending)
6. Add a new distributor
7. Add a new category
8. Register a new user
9. Place a new order
10. List products under a stock threshold (low-stock query)

---

## 🗄 Database Setup

The SQL script to create the relational schema is located at:

```
src/main/resources/sql/create_tables.sql
```

To set up the database:

```sql
CREATE DATABASE store_inventory;
USE store_inventory;
SOURCE create_tables.sql;
```

Update the JDBC credentials inside `DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/store_inventory";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

---

## ▶️ How to Build & Run

### Option 1 — Using `javac`

```bash
# From the project root
cd src/main/java
javac com/gestiune/store/app/Main.java
java com.gestiune.store.app.Main
```

### Option 2 — Using an IDE

1. Import the project as a Java project.
2. Make sure the JDBC driver (e.g., `mysql-connector-j.jar`) is in the classpath.
3. Run `com.gestiune.store.app.Main`.

---

## 📝 Audit Log

Every time one of the 10 main actions is executed, an entry is appended to:

```
audit.csv
```

CSV format:

```
action_name, timestamp
ADD_PRODUCT, 2025-01-15T10:23:45
PLACE_ORDER, 2025-01-15T10:24:01
```

The audit service is implemented as a **Singleton** , ensuring a single shared writer instance.

---

## 🎓 Academic Context

This project was developed as part of an **Object-Oriented Programming** course assignment and demonstrates:

- Class design and OOP principles (encapsulation, inheritance, polymorphism, abstraction)
- Java Collections Framework (including sorted collections)
- JDBC and relational database integration
- Design patterns: **Singleton** , **Generic Repository** - File I/O for auditing

---

## 📄 License

This project is provided for educational purposes. You may freely use, modify, and distribute it under the **MIT License** .
