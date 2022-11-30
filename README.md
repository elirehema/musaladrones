## Musala Soft Drones Task
-- -- 
A is a Java Spring Boot project to handle drone medication loading and unloading.
The project was developed by Elirehema Paul from 29 Nov 2022 to 30 Nov 2022
 ---
### Project structure
* musala.project.drone
  * api
  * data
  * exceptions
  * model
  * repository
  * schedules
  * services
  * utils
  * - DroneApplication

#### API
The directory contains REST endpoints controllers

#### Data
This directory contains Other Models classes which are not database entities

#### Exceptions
The directory for exception handling configurations. For this project it has single Exception handler class for other exception we just return the warning message to avoid Internal server errors.

#### Model
The directory contains POJO classes for database table mapping i.e. Entities

#### Repository
The directory contains all CRUD repository implementations of Models

#### Schedules
This director contains all classes responsible for scheduling activities e.g Battery level scheduler task

#### Services
This contains all Medication and Drone read and write operations. All read and write operations are separated for clarity
  * `DroneReadPlatformService.java` //Drone read operation interface
  * `DroneReadPlatformServiceImpl.java` // Implementation of drone read interface
  * `DroneWritePlatformService.java` // Drone write operation interface
  *   `DroneWritePlatformServiceImpl.java` // Implementation of drone write interface
  * Medication R&W etc

### Utils
This contains utility classes and swagger configurations


### Data Source
The platform uses H2 is an embedded, open-source, and in-memory database

### Scheduler Job
The task to check battery level is configured to run after every 1 minute. 
All the schedules are stored in database

## Getting Started
To run the app go to the project root director and run `mvn spring-boot:run` command.
The command will launch the app and reload database with default drones. 
After the project launch you can access the H2 database console in http://localhost:8080/api/h2-console

The application run in port `8080` with context URI `/api/` i.e all endoints must start at `{PORT NUMBER}/api/{OTHER ENDPOINTS}`
E.g `http://localhost:8080/api/drones` 


### Swagger Documentation
After project launching you can access SWAGGER API Documentation at http://localhost:8080/api/swagger-ui.html
All swagger API endpoints contains descriptions on what it does just below it.


### Postman
If you would prefer to use Postman for testing you can load the postman collection file stored in `ROOT/src/main/resources/static/Musala.postman_collection.json`

### H2 Console
To access H2 console use the credentials provided in `application.properties`<br>URL:  http://localhost:8080/api/h2-console

### NOTE
Except for `{BASRE_URL}/drones/{DRONE-ID}/medications` endpoint for loading drone with medications which users Form Data all Other apis requires Application/Json.
All ENDPOINTS are tested and all working fine.
