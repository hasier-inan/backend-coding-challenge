README
====
Welcome to the backend coding challenge solution! You can clone this project with the following command:

```
git clone https://github.com/hasier-inan/engage-poc
```

###Setup


In order to run the application locally you will need to setup your environment first.

**Java**

You can download and install java directly from the [official web page](https://www.java.com/en/download/) or via _brew_ if you are working with a Mac. Remember setting up the $JAVA_HOME and adding it to the $PATH environment variable. 

**MySQL**

You need to install MySQL and have it running in your machine if you want to launch the application locally.
 You can verify the instance is running with the `mysql.server status` command (you can start/restart the server with `mysql.server start` and `mysql.server restart`, respectively).

The configuration details are defined in the `solution/resources/application.properties` file. You can amend that file with your own database url/port and credentials. The _Expenses_ table will be created from the model, if you don't want to destroy and recreate the same table every time the application is restarted, you can change the `spring.jpa.hibernate.ddl-auto` property. 

**Maven (Optional)**

If you want to run the project directly from the source code you will need to [install Maven first](https://maven.apache.org/install.html) and include the binary folder in the $PATH.


###Running the application

You can run the application from the command line or from an IDE. In any case, once executed it will be listening, by default, at `localhost:8080/`

 **1. Command line**
 
 You can run the application directly using the jar file provided in the project (`dist/backend-coding-challenge-1.0.0.jar `). The backend side has been built using _Spring Boot_ and it has embedded HTTP server in it.
 
 ```
 java -jar ./dist/backend-coding-challenge-1.0.0.jar
 ```
 
 If interested, you can build the jar from the source code , run `mvn package` and it will build the jar in the `target` folder (you will need to setup Maven first). Remember that you'll need to install all required dependencies as well as compile the front-end code:    
 
 ```
 mvn clean install
 gulp dev
 mvn package
 ```
 ```
 java -jar ./target/backend-coding-challenge-1.0.0.jar
 ```
  
 You can also run the application with the following _mvn_ command:
 
 ```
 mvn spring-boot:run
 ```
  
 **2. IDE**
 
 You can import the project in your favourite IDE and run the application from there. The following guide will cover importing the project in IntelliJ:
 
 - Select `File > New > Project from Existing Sources` and choose the root folder of the project.
 - In case IntelliJ doesn't do it for you, mark the `solution/main/java` directory as the _Source Root_, `solution/resources` as the _Resources Root_ and `solution/test/java` as the _Test Sources Root_.     
 - IntelliJ should have recognised the project as a _Spring Boot_ application, so you should be able to run the main app: `solution/main/java/com/engage/Application` (`Run 'Application'`).
 