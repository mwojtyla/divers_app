# REST API DIVER'S APPLICATION

### Introduction

The project of *REST API Diver's Application* contains the backend mechanics 
of the *Diver's Application* and uses two external API.

### Description
The application provides the following services:
* [User](https://docs.gradle.org)
    - [create a User](https://docs.gradle.org)
    - [update a User](https://docs.gradle.org)
    - [delete a User](https://docs.gradle.org)
    - [get data of User](https://docs.gradle.org)
    - [get User's id by login and password](https://docs.gradle.org)
    - [verification of login and password when the User logs in](https://docs.gradle.org)
    - [verification of login availability when the User registers](https://docs.gradle.org)
* [Equipment](https://docs.spring.io/spring-boot/docs/2.7.9/gradle-plugin/reference/html/)
    - [create an Equipment](https://docs.gradle.org)
    - [update an Equipment](https://docs.gradle.org)
    - [delete an Equipment](https://docs.gradle.org)
    - [get data of Equipments by User's id](https://docs.gradle.org)
    - [get Equipment's id by name and User's id](https://docs.gradle.org)
* [Diving Base](https://docs.spring.io/spring-boot/docs/2.7.9/gradle-plugin/reference/html/#build-image)
    - [create a Diving Base](https://docs.gradle.org)
    - [update a Diving Base](https://docs.gradle.org)
    - [delete a Diving Base](https://docs.gradle.org)
    - [get data of Diving Bases by User's id](https://docs.gradle.org)
* [Diver's Log](https://docs.spring.io/spring-boot/docs/2.7.9/reference/htmlsingle/#web)
    - [create a Diver's Log](https://docs.gradle.org)
    - [update a Diver's Log](https://docs.gradle.org)
    - [delete a Diver's Log](https://docs.gradle.org)
    - [get data of Diver's Logs by User's id](https://docs.gradle.org)
    - [get data of Diver's Log by localization and User's id](https://docs.gradle.org)
    - [get data of Diver's Log by depth and User's id](https://docs.gradle.org)

### How to run
To run the application:
1. Create a schema named `diver_crud` in MySQL Database,
2. Create a user named `mwojtyla` with password `diver123` and grant him permissions to this schema,
3. Open a backend application in Intellij IDEA and run,
4. Download a frontend application available at the link: \
https://github.com/mwojtyla/divers_app_frontend, \
open it in Intellij IDEA and run it too.
5. Open a web browser and enter the link: \
http://localhost:8080/main-view \
to show the main paige of the frontend application.

_Note: frontend runs on default server port (8080), but backend runs on server port 8090._

### External API

* Weather API: https://rapidapi.com/weatherapi/api/weatherapi-com
* Currency Exchange API: https://rapidapi.com/fyhao/api/currency-exchange

### Technologies

* Java 17
* SpringBoot
* SpringWeb
* MySQL
* H2 Database
* Hibernate
* Lombok
* Mockito
* Gradle
* JUnit5




