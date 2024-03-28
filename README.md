
**Shop Management System**

**Description**:
This Java JDBC project, named "Shop", is a comprehensive shop management system designed to streamline inventory operations. Utilizing the PostgreSQL database for data storage, the system follows the Model-View-Controller (MVC) architecture, ensuring modularity and scalability. Users can seamlessly add, fetch, remove, and update products within their shop, with robust validation mechanisms in place to maintain data integrity.

**Features**:

Add Product: Easily add new products to the shop inventory with relevant details such as name, price, quantity, etc.<br>
Fetch Product: Retrieve product information based on various criteria such as ID, name, or category.<br>
Remove Product: Efficiently remove products from the inventory, updating the database accordingly.<br>
Update Product: Modify existing product details including price, quantity, or any other relevant attributes.<br>

**MVC Structure**:

**Model**: Contains the business logic and data access layer, handling interactions with the database (PostgreSQL). Classes for database connectivity and CRUD operations reside here.<br>
**View**: Responsible for presenting the user interface and interacting with users. In this project, it includes classes for displaying menus, prompts, and receiving user inputs.<br>
**Controller**: Acts as an intermediary between the Model and View. It processes user requests, invokes appropriate methods from the Model, and updates the View accordingly. Controller classes handle the business logic flow.

**Usage**:

Ensure that PostgreSQL is installed and running.<br>
Set up the database schema using the provided SQL scripts.<br>
Configure the database connection parameters (URL, username, password) in the JDBC connection class.<br>
Compile the Java source files using any Java compiler.<br>
Run the application using the main entry point class.

**Dependencies**:

Java Development Kit (JDK)<br>
PostgreSQL database
