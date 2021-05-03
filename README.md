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
`curl -H "Content-Type: application/json" -X POST "http://localhost:8080/login" -d '{ "username": "admin", "password": "admin" }'`

#### Normal User
`curl -H "Content-Type: application/json" -X POST "http://localhost:8080/login" -d '{ "username": "user1", "password": "user1" }'`
`curl -H "Content-Type: application/json" -X POST "http://localhost:8080/login" -d '{ "username": "user2", "password": "user2" }'`

### Resources

Excepting the `/login` API endpoint that is being used for authentication, all the other API endpoints require a HTTP header to be passed in as parameter in all requests.
This HTTP header will have the key name "Authorization" and the key value will be the authentication token that was retrieved during the authentication transaction (please see the `Authentication` section).
Note: this token is a JWT token that is internally signed with a private key configured in the server, and it has the public user information on it.
The idea behind this JWT token is to avoid going back to the database to validate whether a JWT token is valid or not, because that information is already contained in the token itself.
There are several approaches for authentication such as Cookies, GUID tokens, OAuth2, etc... but we are choosing JWT for simplicity and scalability purposes.

Note: we are using HATEOAS-oriented REST endpoints (https://en.wikipedia.org/wiki/HATEOAS), so you will find the possible operations to perform on resources while browsing the main endpoints: `/products` and `/categories`
In the below examples, you need to replace `XXXX` with the token returned in the authentication process (please see the `Authentication` section for more information).

#### Products

The list of Products is always a paginated result for scalability.

URL: `/products`

* To get (paged) list of products: `curl -H "Authorization: XXXX" -X GET "http://localhost:8080/products"`
* To get products info: `curl -H "Authorization: XXXX" -X GET "http://localhost:8080/products/{id}"`
* To create products: `curl -H "Content-Type: application/json" -H "Authorization: XXXX" -X POST "http://localhost:8080/products"  -d '{ "name": "P1", "currency": "EUR", "price": 100.00 }'`
* To update products: `curl -H "Content-Type: application/json" -H "Authorization: XXXX" -X PUT "http://localhost:8080/products/{id}" -d '{ "name": "P1", "currency": "EUR", "price": 100.00 }'`
* To delete products: `curl -H "Authorization: XXXX" -X DELETE "http://localhost:8080/products/{id}"`

#### Categories

URL: `/categories`

* To get list of categories: `curl -H "Authorization: XXXX" -X GET "http://localhost:8080/categories"`
* To get category info: `curl -H "Authorization: XXXX" -X GET "http://localhost:8080/categories/{id}"`
* To create category: `curl -H "Content-Type: application/json" -H "Authorization: XXXX" -X POST "http://localhost:8080/categories"  -d '{ "name": "C1" }'`
* To update category: `curl -H "Content-Type: application/json" -H "Authorization: XXXX" -X PUT "http://localhost:8080/categories/{id}" -d '{ "name": "C1" }'`
* To delete category: `curl -H "Authorization: XXXX" -X DELETE "http://localhost:8080/categories/{id}"`

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
