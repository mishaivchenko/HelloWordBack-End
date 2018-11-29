[![Build Status](https://travis-ci.org/mishaivchenko/HelloWordBack-End.svg?branch=master)](https://travis-ci.org/mishaivchenko/HelloWordBack-End)


The application uses spring boot and Jetty's embedded servlet container.
Database - mariaDb
Test database - Spring embedded H2.

To run db migrations - mvn liquibase:update.
To install - mvn clean install.
To run application - java -jar gs-spring-boot-0.1.0.jar.