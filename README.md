# 🎓 CampusResolve – Smart Campus Complaint Management System

CampusResolve is a full-stack web application that simplifies the process of reporting, tracking, and managing campus complaints. It provides a secure platform where students can submit complaints and monitor their status, while administrators can efficiently review, manage, and resolve them.

---

## 🚀 Features

### 👨‍🎓 Student Module

* Secure Registration & Login
* JWT Authentication
* Raise New Complaints
* View Complaint History
* Track Complaint Status
* Responsive Dashboard

### 👨‍💼 Admin Module

* Secure Admin Login
* Dashboard with Complaint Statistics
* View All Complaints
* Search Complaints by Student Name
* Filter Complaints by Status
* Update Complaint Status
* Complaint Details Modal
* Pagination for Complaint Table

---

## 🛠️ Tech Stack

### Frontend

* React.js
* React Router DOM
* Axios
* HTML5
* CSS3

### Backend

* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Tools

* IntelliJ IDEA
* VS Code
* Postman
* Git & GitHub

---

## 📂 Project Structure

```
CampusResolve
│
├── frontend
│   ├── src
│   ├── components
│   ├── pages
│   ├── css
│   └── App.jsx
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── security
│   └── config
│
└── database
```

---

## 🔐 Authentication

* JWT Based Authentication
* Role-Based Authorization
* Student Role
* Admin Role
* Protected Routes

---

## 📊 Admin Dashboard

The Admin Dashboard provides:

* Total Complaints
* Pending Complaints
* In Progress Complaints
* Resolved Complaints
* Search Functionality
* Status Filter
* Pagination
* Complaint Details
* Status Update

---

## 📌 Complaint Workflow

```
Student Login
      │
      ▼
Raise Complaint
      │
      ▼
Pending
      │
      ▼
In Progress
      │
      ▼
Resolved
```

---

## ⚙️ Installation

### 1. Clone Repository

```bash
git clone https://github.com/swamini10/CampusResolve.git
```

### 2. Backend

```bash
cd campusresolve
```

Configure `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/campusresolve
spring.datasource.username=root
spring.datasource.password=your_password
```

Run

```bash
mvn spring-boot:run
```

---

### 3. Frontend

```bash
cd campusresolve-frontend
npm install
npm run dev
```

---

## 📸 Screenshots

Add screenshots here:

* Login Page
* Register Page
* Student Dashboard
* Raise Complaint
* My Complaints
* Admin Dashboard
* Complaint Details

---

## 🔮 Future Enhancements

* Email Notifications
* Complaint Categories
* File/Image Upload
* Real-time Notifications
* Analytics Dashboard
* Export Reports (PDF/Excel)
* Dark Mode

---
