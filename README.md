[![Run Status](https://api.shippable.com/projects/5c802246867d9e0700f9e2eb/badge?branch=master)]()
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)

# DefPub REST Service

## Requirements:
- Java 11 or higher
- Maven 3.3 or higher

## How to build and to run:
- to run the app with embeded H2DB just put these properties to 'application.properties' file in app's resources folder:
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
```
- run next commands in the console:
```
mvn clean package
mvn spring-boot:run
```
- open Swagger documentation page in the browser: http://localhost:8080/api/swagger-ui.html
