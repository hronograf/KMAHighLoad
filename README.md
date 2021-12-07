# KMAHighLoad

Usage:
- to run project, go to project directory and run in cmd `docker-compose up`
- to make testing easier, project contains `postman_collection.json` file,<br/> 
also, after application starts, pgadmin4 wiil be available at `localhost:5050` with password `admin`,<br/> 
to connect to database, you can create new server inside pgadmin4:<br/> `hostname: postgres, port: 5432, maintenance databse: book_project, user: root, password: root`,<br/> 
RabbitMq client wiil be available at `localhost:15672` with login and password `guest`,<br/>
to connet to Redis, run in cmd `docker exec -it redis redis-cli`, after that run `keys *` inside redis-cli to check cache content
<br/>

Project endpoints:
- POST /book/create
- POST /book/delete
- GET /book/{id}
- GET /book/all

For "create" or "delete" requests BookApi sends message to RabbitMq queue, then DbWorker receives it and writes to database
<br/>
For "get by id" or "get all" requests BookApi tries to read from Redis cache and, if it does not find any, reads from database
<br/>
<br/>

Technologies used:
- Queue: RabbitMq
- Database: PostgreSQL
- Cache: Redis
<br/>

Project parts:
- BookApi - SpringBoot application, entrypoint of application
- Common - SpingBoot library for common classes (like RabbitMqConfig, BookEntity, etc.)
- DbWorker - SpringBoot application, writes to the database
<br/>




