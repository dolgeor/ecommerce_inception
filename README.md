# README #
  
#### Prerequisites
 
* Git

* Maven

* Java 8
 
### Running the project with DB in a Docker Container

Steps:

###### Install the core jar in the local maven repository:

From root directory run the following command:

```
$ cd core
$ mvn clean install
```

###### Running the console project:

From root directory run the following command:

```
$ cd console
$ mvn clean package
$ java -jar target/console-1.0-SNAPSHOT.jar 
```

###### Running the web project:

From root directory run the following command:

```
$ cd mvc
$ mvn clean package
```

Then, configure the IDE to run a tomcat server, deploy the generated war (in target) and run tomcat.

Voila, you're set up!

Access the app on the localhost, by typing in browser:

```
localhost:8080/
```
