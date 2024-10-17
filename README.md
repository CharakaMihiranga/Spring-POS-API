# Spring POS API

The **Spring POS API** is a robust backend solution designed for a Point of Sale (POS) system, developed using the **Spring Framework**. It facilitates seamless integration with front-end applications by efficiently managing orders, customers, and inventory through a well-designed API.

## Features

* **RESTful API:**
   * A comprehensive set of endpoints allows for managing customers, items, and orders.
   * Adheres to standard HTTP methods (GET, POST, PUT, DELETE) to ensure intuitive integration with any front-end application.
* **Layered Architecture:**
   * Utilizes a well-structured layered architecture that separates concerns into Controller, Service, and DAO layers.
   * Promotes maintainability and scalability, making future enhancements easier to implement.
* **Database Integration:**
   * Employs **MySQL** as the relational database, utilizing **Spring Data JPA** and **Hibernate** for efficient data access and manipulation.
* **Logging:**
   * Implements logging via **SLF4J** with **Logback**, supporting various logging levels (INFO, DEBUG, ERROR) for thorough tracking and debugging throughout the application lifecycle.
* **AJAX Support:**
   * Supports AJAX for asynchronous data handling, enhancing user experience by allowing operations without full page reloads.

## Tech Stack

* **Framework:** Spring Framework
* **Database:** MySQL
* **ORM:** Hibernate (Object-Relational Mapping)
* **Data Access:** Spring Data JPA
* **Web Technology:** Spring Web MVC
* **Logging:** SLF4J with Logback
* **Mapping:** ModelMapper (for conversion between DTOs and entities)

## Installation

### Prerequisites:
* **Java 17** or higher
* **Maven**
* **MySQL**

### Steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/CharakaMihiranga/Spring-POS-API.git
   cd Spring-POS-API
   ```

2. **Set Up MySQL Database:**
   * Create a database named `springpos` using your MySQL client:
     ```sql
     CREATE DATABASE springpos;
     ```

3. **Configure Database Credentials:**
   * Update the database configuration in the `WebAppRootConfig` class with your specific MySQL credentials and connection URL.

4. **Build the Project:**
   ```bash
   mvn clean install
   ```

5. **Run the Application:**
   * Deploy the generated WAR file located in the `target` directory to your preferred servlet container (e.g., Tomcat).

## API Documentation

For detailed information on endpoints, request/response formats, and usage examples, please refer to the API documentation:

https://documenter.getpostman.com/view/35384500/2sAXxV7Vvw

## Contributing

Contributions are welcome! If you have suggestions for improvements or new features, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
