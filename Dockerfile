FROM amazoncorretto:17
COPY target/api-crud-1.0-SNAPSHOT.jar /app/api-crud-1.0-SNAPSHOT.jar
WORKDIR /app
CMD ["sh", "-c", "set && java -jar -Dspring.profiles.active=deploy api-crud-1.0-SNAPSHOT.jar"]