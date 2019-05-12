# abcpizza
Hackerrank abcpizza - API for finding status of Pizza ordered

Module details -
spring-cloud-config-server - 
adminserver -  Provides an administrative interface for our Spring Boot applications.
registry - Service registration and discovery (Eureka)
gateway - The API Gateway is responsible for request routing, composition, and protocol translation. All requests from clients first go through the API Gateway.
mysql - Database for Order status microservice

orderstatus - Microservice for managing order status updates and serving service calls for finding order status for given phone number or order number.
It subscribes to a Kafka queue for order status update events. The same is stored in DB.

monitor - Monitoring microservice


