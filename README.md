[![Run Status](https://api.shippable.com/projects/5c802246867d9e0700f9e2eb/badge?branch=master)](https://app.shippable.com/github/gleb-kosteiko/defpub-rest-service/dashboard/jobs)
[![Coverage Badge](https://api.shippable.com/projects/5c802246867d9e0700f9e2eb/coverageBadge?branch=master)](https://app.shippable.com/github/gleb-kosteiko/defpub-rest-service/dashboard/insights)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)

# DefPub REST Service

## Requirements:
- Java 11 or higher
- Maven 3.3 or higher
- Docker (optional)

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

## How to build and to run with Docker:
- build app with Maven as described in steps above
- build Docker image
```
docker build -t defpub-rest-service .
```
- run Docker container
```
docker run -p 8080:8080 defpub-rest-service
```

## Useful links:
- Swagger documentation: http://localhost:8080/api/swagger-ui.html
