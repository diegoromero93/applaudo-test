# Applaudo Test

:wave: Hello Applaudo Studio Evaluator :)

This is a small project por the code challenge that you sent to me


## Initial data

there are 2 users created for testing this app

| Username       | Password  | Role  |
|----------------|:---------:|-------|
| diegoromero    |  admin    | ADMIN |
| diegoromero1   |  nonadmin | USER  |


## Running this app

Clone the repository, ensure that all variables are set  and make the following steps

+ Clone or download the project
+ Install Dependencies with Maven 
```bash
mvn clean install
```

Please ensure that H2 variables are set, they have a pre configured set up anyway

```bash
spring.datasource.url=
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=
spring.datasource.password=
```

Also ensure that jwt variables are set

```bash
app.jwtSecret=
app.jwtExpirationInMs=
```

And by default I have two other props variables **ddl-auto=create** will automatically create the db structure and **populate** will create the initial data to play with

```bash
spring.jpa.hibernate.populate=true
spring.jpa.hibernate.ddl-auto=create
```

+ Import project to IDE
+ :white_check_mark: And enjoy testing 
```bash
mvn spring-boot:run
```
 ## Postman API Collection
 
 To test please import the following collection

[Click here](https://www.getpostman.com/collections/110832851f1bb9301b95)

Keep in mind the following information

| Request       | HTTP METHOD  | JWT Token on header required  | ROLE |
|----------------|:---------:|:-------:|:-------:|
| /api/auth    |  POST    | no | no role |
| /app/item   |  POST | yes  | USER / ADMIN |
| /app/item/{itemId}   |  PUT | yes  | USER / ADMIN |
| /app/item/{itemId}  |  DELETE | yes  | ADMIN |
| /app/item  |  DELETE | yes  | ADMIN |
| /app/item  |  GET | no  | no role |
| /app/item/{itemId}  |  GET | no  | no role |
| /app/item?itemStatus={status}&itemEnteredByUser={enteredBy}  |  GET | no  | no role |
| /app/item?pageSize={pageSize}&page={page}&sortBy={sortByField}  |  GET | no  | no role |