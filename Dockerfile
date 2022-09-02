FROM maven:3.8.6-amazoncorretto-17 as build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn package -Dmaven.test.skip

FROM openjdk:17
COPY --from=build target/KBE-Warehouse-0.0.1-SNAPSHOT.jar KBE-Warehouse-0.0.1-SNAPSHOT.jar
ARG PORT=8081
EXPOSE ${PORT}
cmd java -Dserver.port=$PORT $JAVA_OPTS -jar KBE-Warehouse-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "KBE-Warehouse-0.0.1-SNAPSHOT.jar"]