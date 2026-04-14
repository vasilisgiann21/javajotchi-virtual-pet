# Javajotchi: A Multi-Threaded Virtual Pet Simulator

Javajotchi is a robust terminal-based virtual pet simulation developed in Java. Unlike standard console applications, this project implements advanced concurrency patterns to manage pet vitals in real-time, simulating a "living" system that operates independently of user input.

## 🛠 Technical Highlights

This project was built to demonstrate proficiency in Object-Oriented Programming (OOP) and System-Level Concurrency:

### 1. Multi-Threading & Real-Time Systems
* **Heartbeat Mechanism:** Utilizes `ScheduledExecutorService` to implement a recurring "heartbeat" for each pet. Every 5 seconds, the system automatically decrements vitals (Hunger, Happiness) without blocking the main execution thread.
* **Daemon Threads:** The background scheduler is configured with `thread.setDaemon(true)`. This ensures that background processes do not prevent the JVM from shutting down once the main interaction loop finishes or all pets are deceased.

### 2. Concurrency & Thread Safety
* **Atomic Operations:** Employs `AtomicInteger` for the `activePets` counter. This prevents race conditions in multi-threaded environments where multiple pets might reach a terminal state (death) simultaneously, ensuring the global pet count remains accurate and consistent.

### 3. Object-Oriented Architecture
* **Abstraction & Inheritance:** Features an `abstract class GeneralPet` as the blueprint, with specialized implementations (`Dog`, `Cat`) that override behavioral methods like `makeSound()`.
* **Encapsulation:** Statistics (Hunger, Happiness) are managed through a dedicated `Stat` class, protecting the integrity of value ranges and providing modular upgrade capabilities.

### 4. Advanced Logic
* **State Evolution:** Implements a "Happiness Ticks" system. If a pet's happiness remains above a threshold for a sustained period (24 ticks), it triggers an evolution into a "Super Pet," boosting its maximum stats dynamically.
* **Robust Input Handling:** Implements precise exception handling for CLI interactions using `InputMismatchException` to ensure the program recovers gracefully from invalid user data.

## 📂 Project Structure

```text
.
├── src/
│   ├── Main.java           # Entry point & Interaction Loop
│   ├── GeneralPet.java     # Core Abstract Logic & Thread Management
│   ├── Dog.java            # Concrete Implementation (Inheritance)
│   ├── Cat.java            # Concrete Implementation (Inheritance)
│   └── Stat.java           # Encapsulated Stat Management
├── .gitignore              # IDE & Build Artifact Exclusion
└── README.md               # Documentation
```

## 🚀 Getting Started

### Installation & Execution

**1. Clone the repository:**
```bash
git clone [https://github.com/vasilisgiann21/javajotchi-virtual-pet.git](https://github.com/vasilisgiann21/javajotchi-virtual-pet.git)
cd javajotchi-virtual-pet
```

**2. Compile the source files:**
```bash
javac src/*.java
```

**3. Run the application:**
```bash
java -cp src Main
```

## 🧠 Concepts Applied
* **Concurrency:** `java.util.concurrent`
* **Thread Safety:** `AtomicInteger`
* **Resource Management:** `ExecutorService` shutdown protocols
* **OOP:** Polymorphism, Abstraction, and Encapsulation
