# Inventory Management System

## Description

The Inventory Management System is a simple RESTful API built with Spring Boot that allows users to manage products in a store's inventory. The system supports operations such as viewing a list of available products, adding new products, and updating existing products. The product data is persisted in a MySQL database using Hibernate.

## Features

- View a list of all available products.
- Add new products to the inventory.
- Update existing product details.
- Store product information including name, description, price, and quantity.

## Technologies Used

- Java 17
- Spring Boot 3.3.2
- Hibernate
- MySQL
- Maven
- JUnit 5
- JaCoCo (for code coverage)
- Checkstyle (for code style checks)
- SonarQube (for code quality analysis)

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.x](https://maven.apache.org/download.cgi)
- [MySQL](https://www.mysql.com/downloads/)
- [SonarQube](https://www.sonarqube.org/downloads/)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/bshevyrov/epam-low-task2
cd epam-low-task2 
```
### 2. Configure Environment Variables
```bash
SPRING_DATASOURCE_USERNAME=your_mysql_username
SPRING_DATASOURCE_PASSWORD=your_mysql_password
SPRING_DATASOURCE_URL=your_mysql_schema_address
SPRING_JPA_DATABASE_PLATFORM=your_mysql_dialect
```
### 3. Build the Application
```bash
mvn clean install
```
### 4. Run the Application
```bash
mvn spring-boot:run
```


## Chat GPT log


[chat.html](chat.html)