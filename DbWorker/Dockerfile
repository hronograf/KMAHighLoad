FROM maven:3.6-jdk-11 as builder
WORKDIR /app

COPY BookApi/pom.xml BookApi/pom.xml

COPY Common/src Common/src
COPY Common/pom.xml Common/pom.xml

COPY DbWorker/src DbWorker/src
COPY DbWorker/pom.xml DbWorker/pom.xml

COPY pom.xml pom.xml

RUN mvn clean package -pl DbWorker,Common

FROM openjdk:11.0-jre
WORKDIR /app

COPY --from=builder /app/DbWorker/target/DbWorker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","app.jar"]