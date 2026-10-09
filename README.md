# 🐾 PetConnect

A Java-based desktop application for managing pet adoption and connecting pet owners with potential adopters.

## 📌 Project Overview

**PetConnect** is a desktop-based Pet Adoption Management System developed using **Java Swing, MySQL, and JDBC**.

The application allows users to register and log in, add pets for adoption, browse available pets, add pets to a wishlist, send adoption requests, manage received requests, and view adoption history.

The project demonstrates Java concepts such as **Object-Oriented Programming, inheritance, polymorphism, interfaces, collections, exception handling, multithreading, synchronization, JDBC, and database operations using DAO classes**.

## ✨ Features

* 🔐 User Registration and Login
* 🐶 Add pets for adoption
* 🔎 Browse and search available pets
* ❤️ Add pets to wishlist
* 📩 Send adoption requests
* ✅ Accept or reject adoption requests
* 👤 View adopter/owner details after an accepted request
* 📜 View adoption history
* 📊 Dashboard statistics
* 🔒 Prevent users from requesting adoption of their own pets
* 💾 Persistent MySQL database storage
* 🧵 Background database operations using `SwingWorker`

## 🛠️ Technologies Used

| Technology        | Purpose                  |
| ----------------- | ------------------------ |
| Java              | Application development  |
| Java Swing        | Graphical User Interface |
| MySQL             | Database                 |
| JDBC              | Database connectivity    |
| MySQL Connector/J | JDBC driver              |
| Git & GitHub      | Version control          |

## 🧱 Project Structure

```text
PetConnect/
├── src/
│   ├── model/
│   │   ├── Pet.java
│   │   ├── User.java
│   │   └── PetOwner.java
│   ├── interfaces/
│   │   └── Adoptable.java
│   ├── service/
│   │   ├── PetManager.java
│   │   └── AdoptionService.java
│   ├── gui/
│   │   ├── LoginFrame.java
│   │   ├── RegisterFrame.java
│   │   ├── DashboardFrame.java
│   │   ├── PetListFrame.java
│   │   ├── AddPetFrame.java
│   │   ├── AdoptPetFrame.java
│   │   ├── AdoptionRequestFrame.java
│   │   ├── AdoptionRequestsFrame.java
│   │   ├── AdoptionHistoryFrame.java
│   │   └── WishlistFrame.java
│   ├── dao/
│   │   ├── UserDAO.java
│   │   ├── PetDAO.java
│   │   ├── AdoptionDAO.java
│   │   ├── AdoptionRequestDAO.java
│   │   ├── WishlistDAO.java
│   │   └── DashboardStatsDAO.java
│   ├── util/
│   │   ├── DatabaseConnection.java
│   │   └── TestConnection.java
│   └── Test*.java
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── RunPetConnect.bat
└── .gitignore
```

## 🗄️ Database

The application uses a MySQL database named:

```text
petconnect
```

The database contains tables for:

* Users
* Pets
* Adoptions
* Adoption Requests
* Wishlist

The `pets` table stores the pet owner using `owner_id`, allowing the application to identify which user owns each pet.

## 🔌 Database Configuration

For security, the MySQL password is **not stored directly in the source code**.

The application reads the database password from the environment variable:

```text
PETCONNECT_DB_PASSWORD
```

On Windows PowerShell, configure it using:

```powershell
setx PETCONNECT_DB_PASSWORD "YOUR_MYSQL_PASSWORD"
```

After setting the variable, restart the terminal or VS Code.

Update the database username/database settings in `DatabaseConnection.java` if required for your local MySQL installation.

## ▶️ How to Run

### Requirements

* Java JDK 26 or compatible Java version
* MySQL Server
* MySQL Workbench (recommended)
* MySQL Connector/J

### 1. Clone the repository

```bash
git clone https://github.com/divyansh-singh06/PetConnect.git
cd PetConnect
```

### 2. Set up MySQL

Create the `petconnect` database and required tables in MySQL.

### 3. Configure the database password

Set the `PETCONNECT_DB_PASSWORD` environment variable as described above.

### 4. Compile

From the project root:

```powershell
javac -cp ".;lib\mysql-connector-j-26.7.0.jar" src\model\*.java src\interfaces\*.java src\service\*.java src\dao\*.java src\util\*.java src\gui\*.java
```

### 5. Run

The project also includes:

```text
RunPetConnect.bat
```

which can be used to launch the application.

## 🧩 Java Concepts Demonstrated

### Object-Oriented Programming

* **Encapsulation** through private fields and getters/setters
* **Inheritance** through `PetOwner extends User`
* **Polymorphism** through the `Adoptable` interface and `Pet` implementation
* **Interfaces** through `Adoptable`

### Collections & Generics

The project uses:

```java
ArrayList<Pet>
```

and other generic collections for managing application data.

### Multithreading

`SwingWorker` is used for background database operations so that database loading does not block the Swing user interface.

### Synchronization

The adoption service includes a synchronized method to demonstrate synchronization during adoption-related operations.

### DAO Pattern

Database operations are separated into dedicated DAO classes such as:

* `UserDAO`
* `PetDAO`
* `AdoptionDAO`
* `AdoptionRequestDAO`
* `WishlistDAO`
* `DashboardStatsDAO`

This keeps database logic separate from the GUI.

## 📸 Application Screens

Screenshots of the application can be added here to demonstrate:

* Login
* Registration
* Dashboard
* Available Pets
* Add Pet
* Adoption Requests
* Wishlist
* Adoption History

## 👨‍💻 Author

**Divyansh Singh**

Computer Science Engineering with specialization in Data science
Galgotias University

## 📄 License

This project was developed as an academic project.
