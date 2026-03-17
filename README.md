# BookMyStayApp – Hotel Booking Management System

## Overview

**BookMyStayApp** is a simplified Hotel Booking Management System designed to demonstrate the practical use of **Core Java and fundamental data structures** in real-world software design.

The system is built **incrementally through multiple use cases**, where each stage introduces new programming concepts and architectural improvements.

The project focuses on **system logic, state management, and data structure usage**, rather than user interface development.

Key challenges addressed in the system include:

* Fair handling of booking requests
* Consistent room inventory management
* Prevention of double-booking
* Scalable and maintainable design

---

# System Architecture Evolution

The application is developed in **six incremental use cases**, each introducing important software engineering concepts.

---

# Use Case 1 – Application Entry & Welcome Message

### Goal

Establish the application entry point and demonstrate how a Java program starts execution.

### Concepts Demonstrated

* Java class structure
* `main()` method as application entry point
* `static` keyword usage
* Console output using `System.out.println()`
* Program execution flow

### Output

Displays the application name and version when the system starts.

Example:

Welcome to BookMyStayApp
Hotel Booking System v1.0

---

# Use Case 2 – Basic Room Types & Static Availability

### Goal

Introduce **object-oriented domain modeling** using inheritance and abstraction.

### Concepts Demonstrated

* Abstract classes
* Inheritance
* Polymorphism
* Encapsulation
* Static variables for availability

### Implementation

Three room types are created:

* Single Room
* Double Room
* Suite Room

Each room contains:

* number of beds
* room price
* room type

Availability is stored using simple variables.

### Limitation

Availability variables are **scattered**, which can cause inconsistent state as the system grows.

---

# Use Case 3 – Centralized Room Inventory Management

### Goal

Replace scattered availability variables with a **centralized inventory system**.

### Concepts Demonstrated

* `HashMap<String, Integer>`
* Centralized state management
* Encapsulation of inventory logic
* Constant-time lookup **O(1)**

### Implementation

Room availability is stored in a single structure:

HashMap<RoomType, AvailableRooms>

Example:

Single Room → 5
Double Room → 3
Suite Room → 2

### Benefit

Provides a **single source of truth** for room availability.

---

# Use Case 4 – Room Search & Availability Check

### Goal

Allow guests to view available rooms **without modifying system state**.

### Concepts Demonstrated

* Read-only access
* Defensive programming
* Separation of concerns
* Domain model reuse

### Implementation

The search service:

1. Retrieves availability from inventory
2. Filters rooms with availability > 0
3. Displays room details and pricing

### Benefit

Prevents accidental modification of inventory during search operations.

---

# Use Case 5 – Booking Request Queue (FIFO)

### Goal

Handle multiple booking requests fairly using a **queue-based request system**.

### Concepts Demonstrated

* Queue data structure
* FIFO (First-Come-First-Served)
* LinkedList implementation
* Request ordering
* Decoupling request intake from allocation

### Implementation

Guest booking requests are stored in:

Queue<Reservation>

Requests are processed in the order they arrive.

Example queue:

Alice → Single Room
Bob → Double Room
Charlie → Suite Room

### Benefit

Ensures **fair and predictable booking request handling**.

---

# Use Case 6 – Reservation Confirmation & Room Allocation

### Goal

Safely allocate rooms and confirm reservations while preventing **double-booking**.

### Concepts Demonstrated

* Set data structure for uniqueness
* HashMap for allocation tracking
* Atomic allocation operations
* Inventory synchronization

### Implementation

Room IDs are generated and stored in:

Set<String> allocatedRoomIds

Room assignments are tracked using:

HashMap<RoomType, Set<RoomIDs>>

Example:

Single Room → {SINGLEROOM-1, SINGLEROOM-2}

### Benefits

* Guaranteed unique room IDs
* Immediate inventory update
* Prevention of duplicate room assignments

---

# Data Structures Used

| Data Structure | Purpose                               |
| -------------- | ------------------------------------- |
| HashMap        | Manage centralized room inventory     |
| Queue          | Handle booking requests in FIFO order |
| LinkedList     | Implementation of Queue               |
| Set            | Ensure unique room IDs                |
| HashMap + Set  | Track allocated rooms by room type    |

---

# Project Structure

BookMyStayApp
│
├── README.md
├── src
│   ├── UseCase1HotelBookingApp.java
│   ├── UseCase2RoomInitialization.java
│   ├── UseCase3InventorySetup.java
│   ├── UseCase4RoomSearch.java
│   ├── UseCase5BookingRequestQueue.java
│   └── UseCase6RoomAllocationService.java

---

# How to Compile and Run

Compile a use case:

javac UseCase6RoomAllocationService.java

Run the program:

java UseCase6RoomAllocationService

You can run each use case independently to observe the system evolution.

---

# Key Learning Outcomes

This project demonstrates how software systems evolve through progressive improvements:

1. Application startup and execution flow
2. Object-oriented domain modeling
3. Centralized state management
4. Safe read-only system operations
5. Fair request ordering using queues
6. Secure allocation with uniqueness guarantees

Students gain practical understanding of **how data structures solve real software engineering problems**.

---

# Author

**Rethick CB**
B.Tech CSE (Data Science)
SRM Institute of Science and Technology
