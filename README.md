# Getting Started

## Requirements

You can use [SDKMAN](https://sdkman.io/) to manage your Java and Maven runtimes, and there is a very good tutorial about how to use SDKMAN [here](https://www.baeldung.com/java-sdkman-intro).

* Java 17

```shell
sdk install java 17.0.3.6.1-amzn
```

* Maven 3.8.5

```shell
sdk install maven 3.8.5
```

## Build and Run

### Using Maven

#### Build

```shell
mvn clean install
```

#### Running only the Tests

```shell
mvn test
```

#### Running the application

##### Test Environment

Test environment will use H2SQL in memory mode to persist your test data

```shell
mvn spring-boot:run
```

or using just Java
```shell
mvn clean install
java -jar target/api-crud-1.0-SNAPSHOT.jar 
```

##### Local Environment

Local environment will use H2SQL in file to persist your changes

```shell
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

or using just Java
```shell
mvn clean install
java -jar -Dspring.profiles.active=local target/api-crud-1.0-SNAPSHOT.jar 
```

##### Prod Environment

Running Docker MariaDB to provide Database support and run the application using your own Machine (for test and debug)

- To create the MariaDB container, if it is not created
```shell
docker run --name db -d -e MARIADB_ROOT_PASSWORD=password -e MARIADB_DATABASE=mydb mariadb:latest
```
- To stop the MariaDB container, if the container is already running
```shell
docker stop db
```
- To restart the MariaDB container, if the container is already created do
```shell
docker start db
```
- To remove the MariaDB container, stop the container first and then
```shell
docker rm db
```

Then, start the application using maven
```shell
mvn spring-boot:run -Dspring-boot.run.profiles=deploy
```
... or execute directly from the JAR file, after you build it usinbg maven
```shell
mvn clean install
java -jar -Dspring.profiles.active=deploy target/api-crud-1.0-SNAPSHOT.jar 
```

To customize the deploy to connect in another `MYSQL` database, please set these **environment variables**:
- **MYSQL_HOST**: connects to the defined host (_default **localhost**_)
- **MYSQL_PORT**: connects to the defined port (_default **3306**_)
- **MYSQL_DATABASE**: select the database as default for opened connection (_default **mydb**_)
- **MYSQL_USERNAME**: connects to database using the username (_default **root**_)
- **MYSQL_PASSWORD**: connects to database using the username (_default **password**_)
- **SQL_QUERIES_SHOW**: show all SQL statements generated by JPA (_default **false**_)
- **SQL_QUERIES_FORMAT**: pretty format all SQL statements generated by JPA (_default **false**_)

Example:
```shell
export MYSQL_HOST=mydb.internal.net
export MYSQL_DATABASE=customer_data
export MYSQL_USERNAME=customers
export MYSQL_PASSWORD=str0ngp@ssw0rd
export SQL_QUERIES_SHOW=true
export SQL_QUERIES_FORMAT=true 
java -jar -Dspring.profiles.active=deploy target/api-crud-1.0-SNAPSHOT.jar 
```

It works because the `application-deploy.yml` profile has placeholders to find the value from the environment variables or use a _default value_. Please realize when you create your local MariaDB container you set the all parameters to match default profile values.

### Using IDE

I used [IntelliJ Idea](https://www.jetbrains.com/idea/) from [JetBrains](https://www.jetbrains.com/)  to build this application but you also can use [Eclipse](https://www.eclipse.org/downloads/), or [Netbeans](https://netbeans.apache.org/download/index.html) to run this application.


## Documentation

### References

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [How to use SDKMAN](https://www.baeldung.com/java-sdkman-intro)
