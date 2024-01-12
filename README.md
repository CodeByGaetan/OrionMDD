# MDD - A developers social network

A complete web application, from Back-end to Front-end, to create a developers social network !

The Front-end uses :  
![Static Badge](https://img.shields.io/badge/Angular-14.1.3-red)
![Static Badge](https://img.shields.io/badge/Angular_Material-14.2.5-blue)

The Back-end uses :  
![Static Badge](https://img.shields.io/badge/Java-11-orange)
![Static Badge](https://img.shields.io/badge/Maven-4.0.0-purple)
![Static Badge](https://img.shields.io/badge/Spring_Boot-2.7.3-green)
![Static Badge](https://img.shields.io/badge/MapStruct-1.5.5.Final-red)
![Static Badge](https://img.shields.io/badge/Passay-1.6.4-darkgreen)
![Static Badge](https://img.shields.io/badge/SpringDoc_OpenAPI_UI-1.7.0-brightgreen)
![Static Badge](https://img.shields.io/badge/MySQL_Connector_Java-8.0.30-blue)
![Static Badge](https://img.shields.io/badge/Lombok-1.18.24-red)

>Spring Boot starter dependencies :  
![Static Badge](https://img.shields.io/badge/Data_JPA-grey)
![Static Badge](https://img.shields.io/badge/Web-grey)
![Static Badge](https://img.shields.io/badge/Validation-grey)
![Static Badge](https://img.shields.io/badge/OAuth2_Resource_Server-grey)

## Getting started

### Clone the project
Clone the project in the directory of your choice :
>git clone https://github.com/CodeByGaetan/OrionMDD.git

### MySQL
- Install MySQL on the localhost and enable the service
- Open a terminal in the directory : `/OrionMDD/back/src/main/resources/sql`
- Connect to MySQL with the root user
- Run `SOURCE reset_db.sql;` to create the database tables
- Run `CREATE USER 'TheUsername'@'%' IDENTIFIED BY 'ThePassword';` to create the MySQL user for the app.
The username and password must be the same than in the application.properties file.
- Run `GRANT ALL ON mdddb.* to 'TheUsername'@'%';` to give MDD App database access to the new user

### Back-end
- Open your IDE (VS Code, Eclipse, etc.) in the directory : `/OrionMDD/back`
- In the file `application.properties` (located at : `/OrionMDD/back/src/main/resources`):
  set the properties `spring.datasource.username` and `spring.datasource.password` with the same username and password than the previously defined MySQL user.
- Run `mvn spring-boot:run` to launch the back-end in developpment mode
- Or run `mvn package` to build the project and then run `java -jar target/mdd-api-0.0.1-SNAPSHOT.jar` to launch the built package.
- Access to API Documentation : http://localhost:3000/swagger-ui/index.html

### Front-end
- Open your IDE (VS Code, Eclipse, etc.) in the directory : `/OrionMDD/front`
- Run `npm install` to install the dependencies
- Run `ng build` to build the project. The build artifacts will be stored in the `/OrionMDD/front/dist/` directory.
- Run `ng serve` to start the front-end development server
- To use the app, navigate to http://localhost:4200/