# SpringBoot-Swing
Swing application combined with SpringBoot JPA using MySQLDatabase

# Prerequisites 
#### Java JDK 17+
#### Docker Desktop - latest version ( for the database ) 

# Libraries
| Library | Description | Version |
| ------------- | ------------- | ------------ |
| SpringBoot | SpringBoot allows easy interaction with the database and defines all the method you need. |  3.2 |
| JPA(Hibernate) | Hibernate ORM enables developers to more easily write applications whose data outlives the application process. | - |
| Swing | Provides a set of "lightweight" (all-Java language) components that, to the maximum degree possible, work the same on all platforms.Flexible and good | - |
| MySQL | Database in which the projec is made | 8.2.1 |
| Lombok | Library to reduce the boilerplate code of Java | - |

#### SpringBoot -- version 3.2.0
#### MySQL database - 8.2.1
#### Lombok
#### Swing
#### Hibernate

# Database infos :
| JDBC URL | USERNAME | PASSWORD |
| ------------- | ------------- | ------------ |
| jdbc:mysql://localhost:3306//doctorsDB  | root  |  PepsiCola30@ |

# How to run 

### On windows 
```
# Run MySQL database schema 
docker-compose up -d

# Build application using maven
mvnw.cmd spring-boot:run
```
### On linux/macOS
```
# Run MySQL database schema 
docker-compose up -d

# Build application using maven
./mvnw spring-boot:run
```
# Screenshots

![diagrama ](https://github.com/MisuStefanLeonard/SpringBoot-Swing/assets/101972228/07866846-f391-45fd-bcbb-f5fb7827ae4f)

<img width="1206" alt="dupa doctor" src="https://github.com/MisuStefanLeonard/SpringBoot-Swing/assets/101972228/c2cbed6c-420c-408d-89dd-4ab6e9a739ad">
<img width="1207" alt="dupa reteta" src="https://github.com/MisuStefanLeonard/SpringBoot-Swing/assets/101972228/2b4b26d6-ce04-4c2a-aab5-711f52816f34">
