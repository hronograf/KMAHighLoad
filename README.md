# KMAHighLoad

Usage:
- to run project, go to project directory and run in cmd `docker-compose up`
- to make testing easier, project contains `postman_collection.json` file
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




