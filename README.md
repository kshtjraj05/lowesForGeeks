# lowesForGeeks
A restful web API for an event hosting platform using Spring Boot, Maria DB server, JPA and Hibernate.

# Requirements
- Install Java 1.8 or higher.
- Add java to environment variables.
- Install Git.
- Install Git Desktop.
- Login to Git Desktop using github id and password.
- Install Git LFS.
- Install a sql database server(preferably MariaDb) with user: root and password : admin on Port 3306.
- If a database server is already installed on port 3306, then on that server run the following query  if the root user is not created : `CREATE USER 'root'@'localhost' IDENTIFIED BY 'admin';`. Otherwise change it's password using the following query: `SET PASSWORD FOR 'root'@'localhost' = PASSWORD('admin');`

# How to run

- Click on `Clone or Download` button.
- Click on `Open in Desktop`.
- Now the repository would open in Github desktop.
- In Github Desktop click on Repository 
- From the drop down menu select show in explorer.
- Right click in the opened folder space.
- Click on `Git bash here`.
- Type `git lfs pull`.
- After the command finishes exectuing, close the git bash.
- Open command prompt in the same directory
- Type java -jar lowesForGeeks-0.0.1-SNAPSHOT.jar
- If the jar file does not run :
-- Install IntelliJ Community Edition
-- Open the loweforgeeks folder as IntelliJ Community Edition Project
-- Run the project.

  # Runtime
  - Once the application starts running, in the CMD or terminal, you would see "Tomcat started on port(s): `your port no.` " in the second last line.
  - All the HTTP requests can be made by prefixing "localhost:`your port number`/`your request url`
  - The different types of requests are given in API.md file.
