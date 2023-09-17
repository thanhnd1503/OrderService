## **Order Service**

## Description
### Order service is an application that helps us place an order with Scala Pay integration API. Our service app is like middleware between the order app (ReactJS App) and the Scale Pay system and makes them works together.

## Features: 
### There are 2 main features: place an order with Scala Pay integration API, redirect to check out URL after placing successfully

## Code Explanation
### 1. `readme.md`: declare all necessary information in our project. That includes: description, features, code explanation, how to test sections. Please read me first!
### 2. `build.gradle`: define all necessary dependencies using in our project
### 3. `resources/application.properties`: define URL connection to connect to order-app (Frontend React App)
### 4. `constant/AppConstant.java`: define Scala Pay order integration API endpoint could help us place an order
### 5. `connection/AppConnection.java`: implement a method to get the connection to Scala Pay API with an authorization token
### 6. `entity/Consumer.java, entity/Item.java, entity/Merchant.java, entity/Order.java, entity/Price.java, entity/Shipping.java, entity/TotalAmount.java`: define all necessary order information used to place an order. All of these classes will centralized at the `Order.java` class
### 7. `dto/OrderDto.java`: define all order information that is used in our order-service (Backend Java App)
### 8. `dto/ScalaOrderDto.java`: define all order information that is a response from the Scala Pay integration API
### 9. `payload/request/OrderCreatingRequest.java`: define a request that includes an authorization token, and order info, and it will be sent to the Scala Pay integration API to place an order
### 10. `payload/response/OrderCreatingResponse.java`: define a response that includes data received from the Scala Pay integration API when placing an order
### 11. `mapper/OrderMapper.java`: define an interface that includes necessary abstract methods used to convert data between entity, DTO, JSON data types
### 12. `mapper/OrderMapperImpl.java`: implement abstract methods defined at the `OrderMapper.java` interface
### 13. `service/OrderService.java`: define an interface that includes an abstract method used for handling business logic, call to Scala Pay integration API to place an order
### 14. `service/OrderServiceImpl.java`: implement an abstract method defined at the `OrderService.java` interface
### 15. `controller/OrderController.java`: define a RESTFul API at our order-service (Backend Java App) that receives a request from an order-app (Frontend ReactJS App) and passes data to the service for placing an order

## How to test:
### 1. Test our Backend API by Postman: to test our RESTFul API (`http://localhost:8080/api/orders`), we need to:
### 1.1. Add authorization bearer token `qhtfs87hjnc12kkos` in Headers
### 1.2. Add request body type JSON like below:
`{
"givenNames":"Nguyen",
"surname":"Thanh",
"email":"thanh@gmail.com",
"phoneNumber":"0987654321",
"countryCode":"vn",
"countryName":"Vietnam",
"postCode":"700000",
"suburb":"No",
"line1":"0987654321",
"itemName":"T-Shirt",
"quantity":"3",
"price":"200",
"category":"Clothing",
"sku":"50595"
}`

### 1.3. Check response data if we have response like below, we call BE API successfully:
`{
"message": "Place an order successfully!",
"scalaOrderDto": {
"token": "X3M1LF232QVR",
"expires": "2023-09-17T06:09:38.000Z",
"checkoutUrl": "https://portal.integration.scalapay.com/checkout/X3M1LF232QVR"
}
}`
### 1.4. Otherwise, we call BE API failed if response data like below:
`{
"message": "Place an order failed!",
"scalaOrderDto": null
}`