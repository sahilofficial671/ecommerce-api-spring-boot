# eCommerce API

## User Requirements

### Create database schema

1. On first run it will create schema, which will allow storing different products, categories, etc. and after that you have to change from "create" to "update" in src/main/resourses/application.properties
Change from spring.jpa.hibernate.ddl-auto=create to spring.jpa.hibernate.ddl-auto=update

2. Currency for product price is INR.

### Develop RESTful API for managing products, categories & orders

A client should be able to perform CRUD operations for Product, Category & Orders resources.

## Assumptions

1. A product might be associated to multiple categories. For instance, an electric toothbrush may belong to both "Electronics" and "Beauty & Personal Care" categories.
2. There are some fields that for simplicity we are not having in Product table such as features, price discount, refurbished, among others.
3. It is a single store ecommerce api.
4. Products belongs to single store owner who is able to update or delete Products, but all Users can see products present in the system.

## How to use this API
- [Authentication](#authentication)
    - [Customer](#customer)
        - [Customer Login](#customer-login)
    - [Admin](#admin)
        - [Admin Login](#admin-login)
- [Products](#products)
    - [Get list of products](#get-list-of-products)
    - [Get product details](#get-product-details)
    - [Create product](#create-product)
    - [Update product](#update-product)
    - [Delete product](#delete-product)

- [Categories](#products)
    - [Get list of categories](#get-list-of-categories)
    - [Get category details](#get-category-details)
    - [Create category](#create-category)
    - [Update category](#update-category)
    - [Delete category](#delete-category)
    
- [Users](#users)
    - [Get list of users](#get-list-of-users)
    - [Get user details](#get-user-details)
    - [Create user](#create-user)
    - [Update user](#update-user)
    - [Delete user](#delete-user)

- [Roles](#roles)
    - [Get list of roles](#get-list-of-roles)
    - [Get role details](#get-role-details)
    - [Create role](#create-role)
    - [Update role](#update-role)
    - [Delete role](#delete-role)
 
- [Orders](#orders)
    - [Get list of orders](#get-list-of-orders)
    - [Get order details](#get-order-details)
    - [Create order](#create-order)
    - [Update order](#update-order)
    - [Delete order](#delete-order)

## Authentication

Currently there are two roles defined as follows:
- Admin: it's a role for backend end user. A super user role that can manipulate categories, products, orders, etc.
- Customer: it's a role for front end customers who can just view, order products

## Admin
##### Admin Login
    - **Method**: POST
    - **URL**: admin/login
    - **Request Data**:  ```{"email": "your@email.id", "password": "your_password"}```
    - **Return**: User model

## Customer
##### Customer Login
    - **Method**: POST
    - **URL**: customer/login
    - **Request Data**:  ```{"email": "your@email.id", "password": "your_password"}```
    - **Return**: User model

#### Resources

## Products
#### Get list of products: 
- **Method**: GET
- **URL**: /products

#### Get product details: 
- **Method**: GET
- **URL**: /product/{id}

#### Create product:
- **Method**: POST
- **URL**: /product/create
- **Request Data**:
    ```
    {
        "name": "Apple",
        "description": "Mango description",
        "quantity": 17,
        "price": 10.0,
        "specialPrice": 5.0,
        "slug": "manog-new",
        "mainImagePath": "product/main/image/path/put/here",
        "categories":[
            {
                "id": 1,
                "name": "fruit",
                "description": "Fruit description"
            }
        ],
        "images": [
            {"path": "product/image/path/put/here"},
            {"path": "product/image/path/put/here"}
        ]
    }
    ```

- **Note**: 
    - Should be a valid product with id
    - Categories should be valid with category id

#### Update product:
- **Method**: PUT
- **URL**: /product/update
- **Request Data**: 
    ```
    {
        "name": "Apple",
        "description": "Mango description",
        "quantity": 17,
        "price": 10.0,
        "specialPrice": 5.0,
        "slug": "manog-new",
        "mainImagePath": "product/main/image/path/put/here",
        "categories":[
            {
                "id": 1,
                "name": "fruit",
                "description": "Fruit description"
            }
        ],
        "images": [
            {"path": "product/image/path/put/here"},
            {"path": "product/image/path/put/here"}
        ]
    }
    ```

- **Note**: 
    - Should be a valid product with id
    - Categories should be valid with category id
        
#### Delete product: 
- **Method**: DELETE
- **URL**: /product/{id}/delete
    
## Categories
#### Get list of categories:
- **Method**: GET
- **URL**: /categories

#### Get category details: 
- **Method**: GET
- **URL**: /category/{id}

#### Create category: 
- **Method**: POST
- **URL**: /category/create
- **Request Data**: ``` {"name": "fruit", "description": "fruit description." } ```
- **Note**: 
    - Should be a valid category with id

#### Update category:  
- **Method**: PUT
- **URL**: /category/update
- **Request Data**: ``` {"id": 1, "name": "fruit", "description": "fruit description." } ```
- **Note**: 
    - Should be a valid category with id

#### Delete category: 
- **Method**: DELETE
- **URL**: /category/{id}/delete
- **Note**: 
    - No product should be assigned to this category for deletion.

## Users
#### Get list of users:
- **Method**: GET
- **URL**: /users

#### Get user details: 
- **Method**: GET
- **URL**: /user/{id}

#### Create user:  
- **Method**: POST
- **URL**: /user/create
- **Request Data**: 
    ```
    {
        "firstName": "Sahil",
        "lastName": "Bhatia",
        "gender": "Male",
        "phone": "9560487757",
        "dateOfBirth":"1998-03-24",
        "email": "sahil@sahilcom",
        "userName":"sahil1",
        "password":"sahil1234",
        "roles":[
            {
                "id": 1,
                "name" : "Admin"
            }
        ]
    }
    ```
- **Note**: 
    - Should be a valid user with id
    - Should be a valid role with id

#### Update user:   
- **Method**: PUT
- **URL**: /user/update
- **Request Data**:
    ```
    {
        "id": 1,
        "firstName": "Sahil",
        "lastName": "Bhatia",
        "gender": "Male",
        "phone": "9560487757",
        "dateOfBirth":"1998-03-24",
        "email": "sahil@sahilcom",
        "userName":"sahil1",
        "password":"sahil1234",
        "roles":[
            {
                "id": 1,
                "name" : "Admin"
            }
        ]
    }
    ```
- **Note**: 
    - Should be a valid user with id
    - Should be a valid role with id

#### Delete user:   
- **Method**: DELETE
- **URL**: /user/{id}/delete

## Roles
#### Get list of roles:
- **Method**: GET
- **URL**: /roles

#### Get role details:
- **Method**: GET
- **URL**: /role/{id}

#### Create role:
- **Method**: POST
- **URL**: /role/create
- **Request Data**:  ``` {"name": "Admin"}```
- **Note**: 
    - Should be a valid role with id

#### Update role:
- **Method**: PUT
- **URL**: /role/update
- **Request Data**: ``` {"id": 1, "name": "Admin"}```
- **Note**: 
    - Should be a valid role with id

#### Delete role:
- **Method**: DELETE
- **URL**: /role/{id}/delete
- **Note**: 
    - Should be a valid role id
    - Should not been assigned to any product yet.

## Orders
#### Get list of orders:
- **Method**: GET
- **URL**: /orders

#### Get order details:
- **Method**: GET
- **URL**: /order/{id}

#### Create order:
- **Method**: POST
- **URL**: /order/create
- **Request Data**: 
    ```
    {
        "comments":"Asd",
        "quantity":10,
        "address":{
            "addressLine1":"House No. 20",
            "addressLine2":"Block F",
            "city":"FBD",
            "state":"HR",
            "pincode":"121001"
        },
        "orderStatus": {
            "id": 1,
            "name": "Pending"
        },
        "items" : [
            {
                "quantity":17,
                "product": {
                    "id": 1,
                    "name": "Apple",
                    "description": "Mango description",
                    "quantity": 17,
                    "price": 10.0,
                    "specialPrice": 5.0,
                    "categories": [
                        {
                            "id": 1,
                            "name": "papaya",
                            "description": "Fruits available here",
                            "slug": "fruits",
                            "createdAt": 1620235977000,
                            "updatedAt": 1620235977000
                        }
                    ],
                    "slug": "manog-new",
                    "createdAt": 1620235980000,
                    "updatedAt": 1620235980000
                }
            }
        ]
    }
    ```
- **Note**: 
    - Should be a valid order with id
    - Should be having valid products with their respective ids
    - Should have valid order status with id

#### Update order:
- **Method**: PUT
- **URL**: /order/update
- **Request Data**:
    ```
    {
        "id": 1,
        "comments":"Asd",
        "quantity":10,
        "address":{
            "addressLine1":"House No. 20",
            "addressLine2":"Block F",
            "city":"FBD",
            "state":"HR",
            "pincode":"121001"
        },
        "orderStatus": {
            "id": 1,
            "name": "Pending"
        },
        "items" : [
            {
                "quantity":17,
                "product": {
                    "id": 1,
                    "name": "Apple",
                    "description": "Mango description",
                    "quantity": 17,
                    "price": 10.0,
                    "specialPrice": 5.0,
                    "categories": [
                        {
                            "id": 1,
                            "name": "papaya",
                            "description": "Fruits available here",
                            "slug": "fruits",
                            "createdAt": 1620235977000,
                            "updatedAt": 1620235977000
                        }
                    ],
                    "slug": "manog-new",
                    "createdAt": 1620235980000,
                    "updatedAt": 1620235980000
                }
            }
        ]
    }
    ```
- **Note**: 
    - Should be a valid order with id
    - Should be having valid products with their respective ids
    - Should have valid order status with id

#### Delete order: 
- **Method**: DELETE
- **URL**: /order/{id}/delete
- **Note**: 
    - No product should be assigned before order deletion.

#### Order Status

* To get list of order statuses: 
    - **Method**: GET
    - **URL**: /order_statuses

* To get Order Status info: 
    - **Method**: GET
    - **URL**: /order_status/{id}

* To create Order Status: 
    - **Method**: POST
    - **URL**: /order_status/create
    - **Request Data**: ``` {"name": "Pending"} ```
    - **Note**: 
        - Should be a valid order_status with id

* To update Order Status: 
    - **Method**: PUT
    - **URL**: /order_status/update
    - **Request Data**: ``` {"id": 1, "name": "Success" } ```
    - **Note**: 
        - Should be a valid order_status with id

* To delete Order Status: 
    - **Method**: DELETE
    - **URL**: /order_status/{id}/delete
    - **Note**: 
        - No orders should have this status assigned.

##### Add / Remove child categories

* To associate / dis-associate a child category with / from a parent category you can use the following URL: `/categories/{parentid}/subcategories/{childid}`
* To see the current child categories for a given category, you can do a GET on `/categories/{parentid}/subcategories`

##### Link / Unlink products

* To link / unlink products with categories you can use the following URL: `/categories/{categoryid}/products/{productid}`
* To see the current products for a given category, you can do a GET on `/categories/{parentid}/products`.
Note: the API will return also products that are being associated indirectly.
That means if a Product is associated with Category B, which is in turn a child of Category A,
then the product is directly associated with Category B, and indirectly associated with Category A.
Accessing to `/categories/A/products` will return that product that is associated with Category A indirectly along with the products being associated directly with the Category A.

## Technologies

* Java 8
* Spring Boot
* Spring Web / MVC
* MySql
* Spring Data (JPA)
* Hibernate (ORM)
