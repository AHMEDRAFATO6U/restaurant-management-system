# 🍽️ Restaurant Management System

A comprehensive **Restaurant Management System** built with **Java Spring Boot** featuring JWT authentication, role-based access control, and RESTful APIs.

🔗 **Live Repository:** [https://github.com/AHMEDRAFATO6U/restaurant-management-system](https://github.com/AHMEDRAFATO6U/restaurant-management-system)

---

## 📋 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Installation & Setup](#-installation--setup)
- [Default Users](#-default-users)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [Author](#-author)
- [License](#-license)

---

## ✨ Features

### 🔐 Authentication & Authorization
- **JWT Authentication** - Secure login with token-based authorization
- **Role-Based Access Control** - Admin, Manager, Staff roles
- **User Registration & Login** - Secure user management
- **Password Encryption** - BCrypt password hashing

### 📋 Core Features
- **Category Management** - Add, update, delete, and view categories
- **Product Management** - CRUD operations for menu items
- **Order Processing** - Create, track, and manage orders
- **Chef Management** - Manage chef profiles and assignments
- **Contact Information** - Manage restaurant contact details

### 🛡️ Security & Performance
- **JWT Token Filter** - Request authentication
- **CORS Configuration** - Cross-origin resource sharing
- **Global Exception Handling** - Consistent error responses
- **Input Validation** - Data validation on all endpoints
- **Audit Trail** - Track created/updated timestamps
- **Swagger Documentation** - Interactive API documentation

---

## 🚀 Tech Stack

| Category | Technology |
|----------|------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.x |
| **ORM** | Spring Data JPA |
| **Security** | Spring Security, JWT |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **API Docs** | Swagger/OpenAPI |
| **Version Control** | Git & GitHub |




## 📬 API Endpoints

### 🔐 Authentication APIs
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/login` | User login | Public |
| POST | `/api/auth/register` | Register new user | Public |

### 📂 Category APIs
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/categories` | Get all categories | All |
| GET | `/api/categories/{id}` | Get category by ID | All |
| POST | `/api/categories` | Create category | Admin |
| PUT | `/api/categories/{id}` | Update category | Admin |
| DELETE | `/api/categories/{id}` | Delete category | Admin |

### 🍕 Product APIs
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/products` | Get all products | All |
| GET | `/api/products/{id}` | Get product by ID | All |
| POST | `/api/products` | Create product | Admin/Manager |
| PUT | `/api/products/{id}` | Update product | Admin/Manager |
| DELETE | `/api/products/{id}` | Delete product | Admin |

### 📦 Order APIs
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/orders` | Get all orders | Admin/Manager |
| GET | `/api/orders/{id}` | Get order by ID | All |
| POST | `/api/orders` | Create new order | All |
| PUT | `/api/orders/{id}` | Update order | Admin/Manager |
| DELETE | `/api/orders/{id}` | Delete order | Admin |

### 👨‍🍳 Chef APIs
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/chefs` | Get all chefs | All |
| GET | `/api/chefs/{id}` | Get chef by ID | All |
| POST | `/api/chefs` | Create chef | Admin |
| PUT | `/api/chefs/{id}` | Update chef | Admin |
| DELETE | `/api/chefs/{id}` | Delete chef | Admin |

### 📞 Contact APIs
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/contact` | Get contact info | All |
| PUT | `/api/contact/{id}` | Update contact info | Admin |

---

## 🔧 Installation & Setup

### Prerequisites
- ☕ Java 17 or higher
- 🗄️ MySQL 8.0 or higher
- 📦 Maven 3.6 or higher
- 🔗 Git

### Step 1: Clone the Repository
```bash
git clone https://github.com/AHMEDRAFATO6U/restaurant-management-system.git
cd restaurant-management-system
