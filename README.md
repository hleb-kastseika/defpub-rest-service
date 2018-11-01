# DefPub REST Service

mvn clean package

mvn spring-boot:run

***

POST http://localhost:8080/api/auth/signup
{
  "username": "{username}",
  "password": "{password}"
}

POST http://localhost:8080/api/auth/signin
{
  "username": "{username}",
  "password": "{password}"
}

GET http://localhost:8080/api/publications
authorization: Bearer {JWT}

GET http://localhost:8080/api/publications/all
authorization: Bearer {JWT}

POST http://localhost:8080/api/publications
authorization: Bearer {JWT}
{
  "message":"test publication"
}

GET http://localhost:8080/api/publications/{id}
authorization: Bearer {JWT}

PUT http://localhost:8080/api/publications/{id}
authorization: Bearer {JWT}
{
  "message":"updated test publication"
}

DELETE http://localhost:8080/api/publications/{id}
authorization: Bearer {JWT}

GET http://localhost:8080/api/users
authorization: Bearer {JWT}

GET http://localhost:8080/api/users/{id}
authorization: Bearer {JWT}

DELETE http://localhost:8080/api/users/{id}
authorization: Bearer {JWT}