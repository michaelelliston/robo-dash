# RoboDash 🚗🍔

A web-based food ordering and autonomous delivery simulation system.

RoboDash is a full-stack application that allows users to browse food
items, place orders, and simulate delivery using robotic couriers across
a mapped campus environment. The system includes a backend API, database
integration, and an interactive frontend interface.

------------------------------------------------------------------------

## Overview

RoboDash simulates a robotic food delivery service operating on a
university campus. Users can browse menu items, place orders, and track
deliveries performed by simulated robots that follow mapped routes.

This project demonstrates: - Full-stack development - REST API design -
Database integration - Pathfinding and routing logic - Frontend
interaction with backend services

------------------------------------------------------------------------

## Features

### User Features

-   User registration and login
-   Secure authentication using JWT
-   Browse food menu
-   Add items to cart
-   Checkout and place orders
-   View order history
-   Track delivery simulation

### System Features

-   Backend REST API
-   Database persistence for orders and users
-   Robot route calculation
-   Dynamic order history retrieval
-   Shopping cart management
-   Delivery animation on the frontend

------------------------------------------------------------------------

## Technologies Used

### Backend

-   Java
-   Spring Boot
-   REST API
-   JDBC
-   MySQL

### Frontend

-   HTML
-   CSS
-   Bootstrap
-   JavaScript
-   Axios

### Tools

-   IntelliJ IDEA
-   Git / GitHub
-   Maven
-   MySQL Workbench

------------------------------------------------------------------------

## Project Architecture

    RoboDash
    │
    ├── backend
    │   ├── controllers
    │   ├── services
    │   ├── dao
    │   ├── models
    │   └── security
    │
    ├── frontend
    │   ├── pages
    │   │   ├── order.html
    │   │   ├── cart.html
    │   │   ├── profile.html
    │   │   ├── order-history.html
    │   │   └── about-us.html
    │   │
    │   ├── js
    │   │   ├── service
    │   │   ├── auth.js
    │   │   └── scripts.js
    │
    └── database
        └── schema.sql

------------------------------------------------------------------------

## Database Schema

### Users

Stores registered users.

  Column     Description
  ---------- --------------------
  user_id    unique user id
  username   login username
  password   encrypted password
  role       user role

### Products

Food menu items.

  Column        Description
  ------------- ------------------
  product_id    unique product
  item_name     food name
  description   item description
  price         item price
  category_id   category

### Orders

Stores user orders.

  Column        Description
  ------------- --------------
  order_id      unique order
  user_id       customer
  total_price   order total
  order_date    timestamp

### Order_Items

Individual items within an order.

  Column          Description
  --------------- ------------------------
  order_item_id   unique item id
  order_id        order reference
  product_id      food item
  quantity        number ordered
  price           price at time of order

------------------------------------------------------------------------

## Installation

### 1. Clone the repository

``` bash
git clone https://github.com/yourusername/robodash.git
```

### 2. Open in IntelliJ

Open the backend project folder in IntelliJ IDEA.

### 3. Configure the database

Create a MySQL database called:

    robotdb

Update the `application.properties` file:

    spring.datasource.url=jdbc:mysql://localhost:3306/robotdb
    spring.datasource.username=your_username
    spring.datasource.password=your_password

------------------------------------------------------------------------

## Running the Project

### Start the Backend

Run the Spring Boot application:

    RoboDashApplication.java

The API will start at:

    http://localhost:8080

### Start the Frontend

Open the site using:

    frontend/pages/index.html

------------------------------------------------------------------------

## API Endpoints

### Authentication

    POST /login
    POST /register

### Products

    GET /products
    GET /products/{id}

### Orders

    POST /orders
    GET /orders/my-orders

### Locations

    GET /locations

------------------------------------------------------------------------

## Frontend Pages

-   **Home** -- landing page
-   **Order Page** -- browse food items
-   **Cart** -- review items and checkout
-   **Order History** -- view previous orders
-   **Profile** -- manage user details
-   **About Us** -- project information

------------------------------------------------------------------------

## Future Improvements

Possible future enhancements:

-   Real-time robot tracking
-   Live delivery map with GPS routes
-   Admin dashboard for managing orders
-   Payment integration
-   Mobile responsive improvements
-   More advanced pathfinding algorithms

------------------------------------------------------------------------

## Contributors

-   Humza Qasim
-   Michael Elliston
-   Shamar Mohammed
-   Kigen Jones
-   Moussa Hassan
-   Mya Allen
-   Uriel Marrufo
-   Rosa Sifuentes

------------------------------------------------------------------------

## License

This project is for educational purposes.
