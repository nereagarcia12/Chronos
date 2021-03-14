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
The application are composed by five microservice.

![Test Coverage](https://github.com/nereagarcia12/Chronos/blob/master/doc/diagram.png)


|Microservice | Objective | Database | API Documentation |
|---|---|---|---|
| ad-service | This microservice is responsible for ads . The add, edit, delete of ads . Also have the categories.  |  ads_database   | https://chronos-ad.herokuapp.com/swagger-ui.html#/ |  
| user-service  | This microservice is responsible of users. Add/Edit/Delete of users  |  user_database |  https://chronos-user.herokuapp.com/swagger-ui.html#/ |   
| transaction-service  | This microservice is responsible of transactions. Send a transaction/ Accept a transaction / Refuse a Transaction  |  transaction_database |  https://chronos-transaction.herokuapp.com/swagger-ui.html#/ |   
| edge-service  | This microservice is the only one exposed public (API GATEWAY PATTERN). Also have  the responsibility of authentication |  no database |  https://chronos-edge.herokuapp.com/swagger-ui.html#/ |   
| eureka-service  | For service discovery and service registry |  no database |  https://chronos-eureka.herokuapp.com/ |   




# Tech stack





# Testing


# Features


# Authentication


