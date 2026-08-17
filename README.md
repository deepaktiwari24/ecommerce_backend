# E-Commerce Ecosystem

An enterprise-grade, high-performance E-Commerce backend built using **Spring Boot 3+**, **Java 17+**, and **MySQL**. This architecture leverages modern software patterns like immutable DTO payloads via Java Records, strict DB schema scaling, secure decoupled public UUID identifiers, and global transaction management.

---

## Key Architectural Highlights

- **Java 17+ Modern Tooling:** Utilises immutable `record` structures for memory-efficient and thread-safe DTO communications.
- **Dual-ID Security Layer:** Internal database indexing runs on fast sequential `Long` primary keys, while all public REST REST APIs expose random `UUID` keys to prevent resource enumeration attacks.
- **Airtight DB Schema & Integrity:** Custom precision scales (`12, 2`) configured for monetary transactions with rigid cascading lifecycles (e.g., User 1:1 Cart, orphan removal automation).
- **Automated Auditing Lifecycles:** Zero-touch auditing configuration using Spring Data JPA (`@CreatedDate`, `@LastModifiedDate`) mapped cleanly to timezone-agnostic standard ISO `Instant` properties.
- **Fail-Fast Error Pipeline:** Custom centralized `@RestControllerAdvice` handling mapping validations gracefully.

---

## Tech Stack & Dependencies

- **Core Framework:** Spring Boot (Data JPA, Spring Security, Validation)
- **Database Layer:** MySQL Community Server
- **Data Layer Utilities:** Lombok, Hibernate ORM
- **Security Standards:** BCrypt Password Hashing

---

## Core Database Architecture Blueprint

The relational engine maps out 7 synchronized domain entities configured under strict relational constraints:

- `User` ↔ `Cart` *(1:1 Bidirectional Cascade Management)*
- `Category` ↔ `Product` *(1:M Hardened No-Cascade Delete Protection)*
- `Cart` ↔ `CartItem` ↔ `Product` *(1:M Layer with Active Orphan Removal)*
- `Order` ↔ `OrderItem` ↔ `Product` *(1:M Financial Ledger Mapping)*

---

## Local Development Environment Setup

### Prerequisites
- **JDK 17** or higher installed.
- **Maven 3.8+** installed.
- Running local instance of **MySQL Server**.

### 1. Environment Variable Variables Mapping
This project segregates execution footprints using external environment tracking. Before boot, export the following variables locally on your machine:

```bash
export DB_USERNAME=your_local_db_username
export DB_PASSWORD=your_secure_db_password
```

### 2. Configuration Setup
Navigate to `src/main/resources/` and make sure your local configurations are matching the localized template layout:

```yaml
# application-dev.yaml (Local Environment)
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true
    username: \${DB_USERNAME}
    password: \${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 3. Compilation and Initialization
Run the application clean wrapper pipeline using your terminal context:

```bash
mvn clean install
mvn spring-boot:run
```

---

## ⏱️ Milestone Roadmap Evolution

- [x] **Milestone 1:** Core Schema Modeling & Abstract Base Auditing Setup
- [x] **Milestone 2:** Resilient Repository Infrastructure Setup
- [x] **Milestone 3:** Service Contract Strategy & Record-driven User Registration Layer
- [x] **Milestone 4:** Automated Structural Exception Handler
- [ ] **Milestone 5:** Spring Security 6 Stateful/Stateless Route Protection Setup
- [ ] **Milestone 6:** Shopping Cart Lifecycle & Checkout Relational Ledger Engines
