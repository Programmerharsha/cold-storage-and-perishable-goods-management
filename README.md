# Cold Storage and Perishable Goods Management

A small J2EE web application for recording perishable products in a cold-storage inventory. It provides an administrator login, a dashboard, and a form for adding products with a category and shelf-life value. Product records are stored in MySQL.

## Features

- Administrator session login and logout
- Add a product with its name, category, and shelf life
- Browser-side validation for required product fields and a positive shelf-life value
- JDBC/MySQL persistence through a DAO layer
- Success and error result pages
- Servlet filter and application lifecycle listener scaffolding

## Technology stack

- Java 21
- Jakarta-era Java EE 8 / Servlet 4.0 APIs (`javax.servlet`)
- Apache Tomcat 9
- JSP, HTML, CSS, and JavaScript
- MySQL 8 and MySQL Connector/J 8.3.0
- Eclipse Dynamic Web Project configuration

## Project layout

```text
src/main/java/com/demo/
├── Bean/Product.java                 Product model
├── DBConnection/DBConnection.java    JDBC connection utility
├── Implementation/ProductDAOImpl.java Product database operations
├── Interface/ProductDAO.java          Product DAO contract
├── Servlet/                           Login, logout, and product servlets
├── Filter/AuthFilter.java             Session-authentication filter
└── Listener/AppListener.java          Application lifecycle listener

src/main/webapp/WEB-INF/
├── Login.jsp                          Login view
├── dashboard.jsp                      Admin dashboard
├── AddProduct.jsp                     New-product form
├── success.jsp / error.jsp            Result views
├── css/style.css                      Application styling
├── js/script.js                       Client-side validation
└── lib/                               Bundled JDBC and supporting JARs
```

## Prerequisites

- JDK 21
- Apache Tomcat 9.0
- MySQL Server 8.x
- Eclipse IDE for Enterprise Java and Web Developers (recommended for this Eclipse project)

## Database setup

Create the database and product table before deploying the application:

```sql
CREATE DATABASE smartdb;
USE smartdb;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    shelf_life INT NOT NULL
);
```

The connection settings currently live in `src/main/java/com/demo/DBConnection/DBConnection.java`:

```java
jdbc:mysql://localhost:3306/smartdb
username: root
password: enter your password here
```

Update these values to match your local MySQL account. Do not commit real production credentials to source control; move them to environment-specific configuration before deploying beyond local development.

## Run locally

1. Import the repository into Eclipse as an **Existing Projects into Workspace** project.
2. Configure a Tomcat 9 runtime in Eclipse and associate it with the project.
3. Create the MySQL database/table shown above and update the JDBC credentials if needed.
4. Clean/build the project and add it to the Tomcat server.
5. Start Tomcat and open the application at:

   ```text
   http://localhost:8080/cold_storage_and_perishable_goods_management_j2ee_project/
   ```

6. The sample credentials coded in `LoginServlet` are:

   ```text
   Username: admin
   Password: admin
   ```
