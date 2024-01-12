# MDD - A developers social network

A complete web application, from Back-end to Front-end, to create a developers social network !

The Front-end uses :  
![Static Badge](https://img.shields.io/badge/Angular-14.1.3-red)

The Back-end uses :  
![Static Badge](https://img.shields.io/badge/Java-17.0.9-orange)
![Static Badge](https://img.shields.io/badge/Spring_Boot-2.6.1-green)
![Static Badge](https://img.shields.io/badge/Maven-3.9.5-purple)

## Getting started

### Clone the project
Clone the project in the directory of your choice :
>git clone https://github.com/CodeByGaetan/OrionMDD.git

### MySQL
- Install MySQL on the localhost and enable the service
- Open a terminal in the directory : `/YogaApp/ressources/sql`
- Connect to MySQL with the root user
- Run `SOURCE reset_db.sql;` to create the database tables
- Run `CREATE USER 'TheUsername'@'%' IDENTIFIED BY 'ThePassword';` to create the MySQL user for the app.
The username and password must be the same than in the application.properties file.
- Run `GRANT ALL ON yogadb.* to 'TheUsername'@'%';` to give YogaApp database access to the new user

### Back-end
- Open your IDE (VS Code, Eclipse, etc.) in the directory : `/YogaApp/back`
- In the file `application.properties` (located at : `/YogaApp/back/src/main/resources`):
  set the properties `spring.datasource.username` and `spring.datasource.password` with the same username and password than the previously defined MySQL user.
- Run `mvn spring-boot:run` to launch the back-end in developpment mode
- Or run `mvn package` to build the project and then run `java -jar target/yoga-app-0.0.1-SNAPSHOT.jar` to launch the built package.

### Front-end
- Open your IDE (VS Code, Eclipse, etc.) in the directory : /YogaApp/front
- Run `npm install` to install the dependencies
- Run `ng serve` to start the front-end development server
- To use the app, navigate to http://localhost:4200/
- By default the admin account is:
    - login: yoga@studio.com  
    - password: test!1234

http://localhost:3000/swagger-ui/index.html
