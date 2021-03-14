Chronos 
======================

# Purpose
The purpose of the project is to make a collaborative time bank application. A time bank is an application in which hours are exchanged for services. When you sign up, you will receive five hours to request services and if you offer services to other users, they will pay you in hours.

The goal is to exchange services without money involved.

# Online website
The website are online and completely deployed on Heroku. (Wait a minute for the first load)

[Home Page](https://mychronos.herokuapp.com/)

[Login Page](https://mychronos.herokuapp.com/login)

[Register Page](https://mychronos.herokuapp.com/register)

[Category Page](https://mychronos.herokuapp.com/categories)

[Service Page](https://mychronos.herokuapp.com/listing)

[My Profile Page](https://mychronos.herokuapp.com/editUser) - You must be registered before access.

# Architecture diagram
The application are composed by five microservice.We have an edge service, its an API Gateway, it is the only public accessible microservice. This microservice also has the authentication made by JWT, it is in charge of managing it just to avoid replicating it in the rest of the microservices.

The ad, transaction and user microservices are accessible internally through the edge service, so they do not "know" about authentication, they are in a demilitarized zone. This services are registered on eureka.

![Architecture](https://github.com/nereagarcia12/Chronos/blob/master/doc/diagram.png)


|Microservice | Objective | Database | API Documentation |
|---|---|---|---|
| ad-service | This microservice is responsible for ads . The add, edit, delete of ads . Also have the categories.  |  ads_database   | https://chronos-ad.herokuapp.com/swagger-ui.html#/ |  
| user-service  | This microservice is responsible of users. Add/Edit/Delete of users  |  user_database |  https://chronos-user.herokuapp.com/swagger-ui.html#/ |   
| transaction-service  | This microservice is responsible of transactions. Send a transaction/ Accept a transaction / Refuse a Transaction  |  transaction_database |  https://chronos-transaction.herokuapp.com/swagger-ui.html#/ |   
| edge-service  | This microservice is the only one exposed public (API GATEWAY PATTERN). Also have  the responsibility of authentication |  no database |  https://chronos-edge.herokuapp.com/swagger-ui.html#/ |   
| eureka-service  | For service discovery and service registry |  no database |  https://chronos-eureka.herokuapp.com/ |   


# Tech stack

The project are done with:

Java 11 with Spring Boot.   
For testing JUnit, Mockito and MockMVC.   
PostgreSQL Database with Spring Data JPA using stack.yml for start databases with docker.   
Service Discovery with Eureka.   
Maven for build tool/dependency.   
Git for version control.   
Spring Security and JWT for authentication   
Heroku with CI/CD for deployments.   
Controller Advices for Exception Handling   
Docker for create databases.   

AngularJS with typescript.   
HTML5, CSS and Boostrap 5.   


# Testing

I have done the testing in three layers:
- Controller Layer: I did the test mocking the service and testing request/response with MockMVC
- Service Layer: I did the test mocking the repository and calling directly the service for test the business logic
- Repository Layer: I did the repository test saving/retrieving information from database.

## Coverage

|Service  |Class | Method | Line |
|---|---|---|---|
|ad-service| 100% | 85% | 86% |
|user-service| 100% | 98% | 96% |
|transaction-service| 100% | 90% | 91% |

# Authentication

# Features - Screenshots

Home
![Home](https://github.com/nereagarcia12/Chronos/blob/master/doc/Inicio.PNG)

Ad Listing
![Ad Listing](https://github.com/nereagarcia12/Chronos/blob/master/doc/Listado.PNG)

Ad Detail
![Ad Detail](https://github.com/nereagarcia12/Chronos/blob/master/doc/Detalle.PNG)

Category List
![Category List](https://github.com/nereagarcia12/Chronos/blob/master/doc/Categorias.PNG)

Login
![Login](https://github.com/nereagarcia12/Chronos/blob/master/doc/Login.PNG)

Sign Up
![Sign Up](https://github.com/nereagarcia12/Chronos/blob/master/doc/Registro.PNG)

Profile
![Profile](https://github.com/nereagarcia12/Chronos/blob/master/doc/Perfil.PNG)

Publish a ad
![Publish a ad](https://github.com/nereagarcia12/Chronos/blob/master/doc/Publicar.PNG)

Transactions
![Publish a ad](https://github.com/nereagarcia12/Chronos/blob/master/doc/Transacciones.PNG)

My Ads
![My Ads](https://github.com/nereagarcia12/Chronos/blob/master/doc/MisAnuncios.PNG)







