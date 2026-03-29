# 🎓 Student Management System (Java Desktop Application)

## 📌 Project Overview

The **Student Management System** is a Java-based desktop application developed using **Swing and JFrame** for the user interface and **JDBC** for database connectivity.
It allows administrators to efficiently manage student records such as adding, updating, viewing, and deleting student information using an SQL database.

This project demonstrates practical implementation of **core Java, GUI development, and database integration**, making it suitable for academic and fresher-level portfolios.

---
## 🛠️ Technologies Used

* **Java (Core Java)**
* **Swing & JFrame** – GUI development
* **WindowBuilder** – UI design
* **JDBC (Java Database Connectivity)**
* **SQL Database (MySQL)**
* **Eclipse IDE**

---

## ✨ Features

* Add new student records
* View all student details
* Update existing student information
* Delete student records
* User-friendly graphical interface
* Real-time database operations using JDBC

---

## 🗂️ Project Structure

```
StudentManagementSystem/
│
├── src/
│   ├── dao/
│   │   └── StudentDAO.java
│   ├── model/
│   │   └── Student.java
│   ├── ui/
│   │   ├── AddStudentFrame.java
│   │   ├── ViewStudentFrame.java
│   │   ├── UpdateStudentFrame.java
│   │   └── DeleteStudentFrame.java
│   └── util/
│       └── DBConnection.java
│
├── database/
│   └── student_db.sql
│
├── README.md
```

---

## 🗄️ Database Structure

**Database Name:** `student_db`

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50),
    course VARCHAR(50),
    marks INT
);
```

---

## 🚀 How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/Student-Management-System.git
   ```
2. Open the project in **Eclipse IDE**
3. Configure the database connection in `DBConnection.java`
4. Import the SQL file into **MySQL**
5. Run the main JFrame class
6. Start managing student records through the GUI

---

## 🎯 Learning Outcomes

* Hands-on experience with **JDBC and SQL**
* Understanding of **MVC-style project structure**
* Practical GUI development using **Swing & WindowBuilder**
* CRUD operations in real-world applications

---

## 👨‍💻 Author

**Mahesh Peetla**
Java Developer | Fresher
📌 Interested in Java, JDBC, Hibernate, and Full Stack Development

---

## 📄 License

This project is open-source and available for learning and educational purposes.

---

Just tell me 👍
