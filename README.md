[![Build Status](https://travis-ci.org/defpub/defpub-rest-service.svg?branch=master)](https://travis-ci.org/defpub/defpub-rest-service)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)

# DefPub REST Service

## Requirements:
- Java 11 or higher
- Maven 3.3 or higher

## How to build and to run:
- if you want to run the app with external instance of H2BD create folder 'config' in the project root and put 'application.properties' file there which should contains next properties:
```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```
- if you want to run the app with embeded H2DB just put these values to 'application.properties' file in app's resources folder:
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
