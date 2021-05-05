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

### Authentication

Currently there are two roles defined as follows:
- ADMIN: it's the super user role that can manipulate categories and products.
- USER: it's a user role that can manipulate *only* products that were created by that user. It can also get data for other users products and categories in the system.

#### Admin User
`curl -H "Content-Type: application/json" -X POST "http://localhost:9001/admin/login" -d '{ "username": "admin", "password": "admin" }'`

#### Normal User
`curl -H "Content-Type: application/json" -X POST "http://localhost:9001/login" -d '{ "username": "user1", "password": "user1" }'`
`curl -H "Content-Type: application/json" -X POST "http://localhost:9001/login" -d '{ "username": "user2", "password": "user2" }'`

### Resources

#### Users

* To get list of users: 
    - **Method**: GET
    - **URL**: /users

* To get user info: 
    - **Method**: GET
    - **URL**: /user/{id}

* To create user: 
    - **Method**: POST
    - **URL**: /user/submit
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

* To update user: 
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

* To delete user: 
    - **Method**: DELETE
    - **URL**: /user/{id}/delete

#### Roles

* To get list of roles: 
    - **Method**: GET
    - **URL**: /roles

* To get role info: 
    - **Method**: GET
    - **URL**: /role/{id}

* To create role: 
    - **Method**: POST
    - **URL**: /role/submit
    - **Request Data**:  ``` {"name": "Admin"}```
    - **Note**: 
        - Should be a valid role with id

* To update role: 
    - **Method**: PUT
    - **URL**: /role/update
    - **Request Data**: ``` {"id": 1, "name": "Admin"}```
    - **Note**: 
        - Should be a valid role with id

* To delete role: 
    - **Method**: DELETE
    - **URL**: /role/{id}/delete
    - **Note**: 
        - Should be a valid role id
        - Should not been assigned to any product yet.
        
#### Products

The list of Products is always a paginated result for scalability.

* To get list of products: 
    - **Method**: GET
    - **URL**: /products

* To get product info: 
    - **Method**: GET
    - **URL**: /product/{id}

* To create product: 
    - **Method**: POST
    - **URL**: /product/submit
    - **Request Data**:
        ```
        {
            "name": "Apple",
            "description": "Mango description",
            "quantity": 17,
            "price": 10.0,
            "specialPrice": 5.0,
            "slug": "manog-new",
            "categories":[
                {
                    "id": 1,
                    "name": "fruit",
                    "description": "Fruit description"
                }
            ]
        }
        ```

    - **Note**: 
        - Should be a valid product with id
        - Categories should be valid with category id

* To update product: 
    - **Method**: PUT
    - **URL**: /product/update
    - **Request Data**: 
        ```
        {
            "id": 1,
            "name": "Mango",
            "description": "Mango description",
            "quantity": 17,
            "price": 10.0,
            "specialPrice": 5.0,
            "slug": "manog-new",
            "categories":[
                {
                    "id": 1,
                    "name": "fruit",
                    "description": "Fruit description"
                }
            ]
        }
        ```

    - **Note**: 
        - Should be a valid product with id
        - Categories should be valid with category id

* To delete product: 
    - **Method**: DELETE
    - **URL**: /product/{id}/delete

#### Categories

* To get list of categories: 
    - **Method**: GET
    - **URL**: /categories

* To get category info: 
    - **Method**: GET
    - **URL**: /category/{id}

* To create category: 
    - **Method**: POST
    - **URL**: /category/submit
    - **Request Data**: ``` {"name": "fruit", "description": "fruit description." } ```
    - **Note**: 
        - Should be a valid category with id

* To update category: 
    - **Method**: PUT
    - **URL**: /category/update
    - **Request Data**: ``` {"id": 1, "name": "fruit", "description": "fruit description." } ```
    - **Note**: 
        - Should be a valid category with id

* To delete category: 
    - **Method**: DELETE
    - **URL**: /category/{id}/delete
    - **Note**: 
        - No product should be assigned to this category for deletion.

#### Orders

* To get list of orders: 
    - **Method**: GET
    - **URL**: /orders

* To get order info: 
    - **Method**: GET
    - **URL**: /order/{id}

* To create order: 
    - **Method**: POST
    - **URL**: /order/submit
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

* To update order: 
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

* To delete order: 
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
    - **URL**: /order_status/submit
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
