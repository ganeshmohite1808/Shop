
**Shop Management System**

**Description**:
This Java JDBC project, named "Shop", is a comprehensive shop management system designed to streamline inventory operations. Utilizing the PostgreSQL database for data storage, the system follows the Model-View-Controller (MVC) architecture, ensuring modularity and scalability. Users can seamlessly add, fetch, remove, and update products within their shop, with robust validation mechanisms in place to maintain data integrity.

**Steps To Create Maven Project**

**Step 1** : Open Eclipse and Press CTRL + N

**Step 2** : Search Maven Project and then click on src folder

**Step 3** : Search Package then create 3 new packages like Model, View and Controller.

**Step 4** : Search for Class and create new Classes in each packages.

**Features**:

Add Product: Easily add new products to the shop inventory with relevant details such as name, price, quantity, etc.<br>
Fetch Product: Retrieve product information based on various criteria such as ID, name, or category.<br>
Remove Product: Efficiently remove products from the inventory, updating the database accordingly.<br>
Update Product: Modify existing product details including price, quantity, or any other relevant attributes.<br>

**MVC Structure**:

**Model**: Contains the business logic and data access layer, handling interactions with the database (PostgreSQL). Classes for database connectivity and CRUD operations reside here.<br>
**View**: Responsible for presenting the user interface and interacting with users. In this project, it includes classes for displaying menus, prompts, and receiving user inputs.<br>
**Controller**: Acts as an intermediary between the Model and View. It processes user requests, invokes appropriate methods from the Model, and updates the View accordingly. Controller classes handle the business logic flow.
**How to Run**

1.Compile and Run:

javac edu/qsp/lms/view/View.java, 

java esu/qsp/lms/view/View

2.Follow On-screen Prompts:

Enter Shop details during initialization.

Choose options to add, remove, update, or get book details.

**Usage**:

Ensure that PostgreSQL is installed and running.<br>
Set up the database schema using the provided SQL scripts.<br>
Configure the database connection parameters (URL, username, password) in the JDBC connection class.<br>
Compile the Java source files using any Java compiler.<br>
Run the application using the main entry point class.

**How it works**
1. User will add a shop for a first time and then afterward it will directly entered into that shop automatically every time.
2. User can add multiple shops.
3. User can add product as many as times user want to add and then it will get stored in the database at one shot.
4. User can remove, update and fetch all product details seamlessly.
