# SWAM course: Parking Management System

A REST API for managing parking zones, parking spots, vehicles, drivers, and parking tickets.

The project is built with Spring Boot and follows a layered architecture. 

## Main Features

The application supports:

* Managing drivers
* Managing vehicles
* Assigning vehicles to drivers
* Managing parking zones
* Managing parking spots
* Assigning parking spots to parking zones
* Managing parking tickets
* Validating incoming API requests
* Using H2 for local development
* Using PostgreSQL for production
* Running automated tests with Maven
* Reporting test coverage to SonarCloud

## Technology Stack

* Java 21
* Spring Boot 4.1.0
* Spring Web MVC
* Spring Data JPA
* Jakarta Validation
* H2 Database
* PostgreSQL
* Maven
* Lombok
* JUnit
* Mockito
* SonarCloud
* JaCoCo

## Project Folder Structure

```text
swam-parking-system/
├── .github/
│   └── workflows/
│       └── ...                         # GitHub Actions workflows
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── myexam/
│   │   │           └── parkingsystem/
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   ├── DriverController.java
│   │   │               │   ├── VehicleController.java
│   │   │               │   ├── ParkingZoneController.java
│   │   │               │   ├── ParkingSpotController.java
│   │   │               │   └── ParkingTicketController.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   ├── driver/
│   │   │               │   ├── vehicle/
│   │   │               │   ├── parking_zone/
│   │   │               │   ├── parking_spot/
│   │   │               │   └── parking_ticket/
│   │   │               │
│   │   │               ├── entity/
│   │   │               │   ├── type/
│   │   │               │   └── ...     # JPA entities and enums
│   │   │               │
│   │   │               ├── exception/
│   │   │               │   └── ...     # Application exceptions and handlers
│   │   │               │
│   │   │               ├── mapper/
│   │   │               │   └── ...     # Entity and DTO conversion
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── ...     # Spring Data JPA repositories
│   │   │               │
│   │   │               └── service/
│   │   │                   └── ...     # Business logic
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── myexam/
│                   └── parkingsystem/
│                       └── ...          # Unit tests
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Architecture

```text
src/main/java/com/myexam/parkingsystem
├── controller
├── dto
│   ├── driver
│   ├── vehicle
│   ├── parking_zone
│   ├── parking_spot
│   └── parking_ticket
├── entity
│   └── type
├── exception
├── mapper
├── repository
└── service
```

The project uses a layered architecture.

```text
HTTP Request
     │
     ▼
Controller
     │
     ▼
Service
     │
     ▼
Repository
     │
     ▼
Database
```

## Domain Model

The main relationships are:

```text
Driver 1 ─── 1 Address

Driver * ─── * Vehicle

Driver 1 ─── * ParkingTicket

Vehicle 1 ─── * ParkingTicket

ParkingZone 1 ─── * ParkingSpot

ParkingSpot 1 ─── * ParkingTicket
```

## API Base URL

When the application runs locally, the default base URL is:

```text
http://localhost:8080
```

All application endpoints start with:

```text
/api
```

---

# API Endpoints

## Driver API

Base path:

```text
/api/drivers
```

| Method | Path                                           | Description                    | Success Status   |
| ------ | ---------------------------------------------- | ------------------------------ | ---------------- |
| POST   | `/api/drivers`                                 | Create a driver                | `201 Created`    |
| GET    | `/api/drivers`                                 | Get all drivers                | `200 OK`         |
| GET    | `/api/drivers/{id}`                            | Get a driver by ID             | `200 OK`         |
| PUT    | `/api/drivers/{id}`                            | Update a driver                | `200 OK`         |
| DELETE | `/api/drivers/{id}`                            | Delete a driver                | `204 No Content` |
| PUT    | `/api/drivers/{driverId}/vehicles/{vehicleId}` | Assign a vehicle to a driver   | `200 OK`         |
| DELETE | `/api/drivers/{driverId}/vehicles/{vehicleId}` | Remove a vehicle from a driver | `200 OK`         |

### Create a Driver

```http
POST /api/drivers
Content-Type: application/json
```

Example request:

```json
{
  "fullName": "Spider Man",
  "email": "spider.man@example.com",
  "phone": "+391234567890",
  "licenseNumber": "AB1234567",
  "address": {
    "street": "Via Street 10",
    "city": "Florence",
    "postalCode": "50100",
    "country": "Italy"
  }
}
```

### Assign a Vehicle to a Driver

```http
PUT /api/drivers/1/vehicles/3
```

This request assigns the vehicle with ID `3` to the driver with ID `1`.

### Remove a Vehicle from a Driver

```http
DELETE /api/drivers/1/vehicles/3
```

---

## Vehicle API

Base path:

```text
/api/vehicles
```

| Method | Path                 | Description         | Success Status   |
| ------ | -------------------- | ------------------- | ---------------- |
| POST   | `/api/vehicles`      | Create a vehicle    | `201 Created`    |
| GET    | `/api/vehicles`      | Get all vehicles    | `200 OK`         |
| GET    | `/api/vehicles/{id}` | Get a vehicle by ID | `200 OK`         |
| PUT    | `/api/vehicles/{id}` | Update a vehicle    | `200 OK`         |
| DELETE | `/api/vehicles/{id}` | Delete a vehicle    | `204 No Content` |

### Create a Vehicle

```http
POST /api/vehicles
Content-Type: application/json
```

Example request:

```json
{
  "licensePlate": "AB123",
  "model": "Mercedes Bens S class",
  "type": "CAR"
}
```

---

## Parking Zone API

Base path:

```text
/api/parking-zones
```

| Method | Path                      | Description              | Success Status   |
| ------ | ------------------------- | ------------------------ | ---------------- |
| POST   | `/api/parking-zones`      | Create a parking zone    | `201 Created`    |
| GET    | `/api/parking-zones`      | Get all parking zones    | `200 OK`         |
| GET    | `/api/parking-zones/{id}` | Get a parking zone by ID | `200 OK`         |
| PUT    | `/api/parking-zones/{id}` | Update a parking zone    | `200 OK`         |
| DELETE | `/api/parking-zones/{id}` | Delete a parking zone    | `204 No Content` |

### Create a Parking Zone

```http
POST /api/parking-zones
Content-Type: application/json
```

Example request:

```json
{
  "name": "Parking zone A",
  "code": "QWE123",
  "description": "something to describe.",
  "hourlyRate": 14.5,
  "active": true
}
```

---

## Parking Spot API

Base path:

```text
/api/parking-spots
```

| Method | Path                                                             | Description                     | Success Status   |
| ------ | ---------------------------------------------------------------- | ------------------------------- | ---------------- |
| POST   | `/api/parking-spots`                                             | Create a parking spot           | `201 Created`    |
| GET    | `/api/parking-spots`                                             | Get all parking spots           | `200 OK`         |
| GET    | `/api/parking-spots/{id}`                                        | Get a parking spot by ID        | `200 OK`         |
| PUT    | `/api/parking-spots/{id}`                                        | Update a parking spot           | `200 OK`         |
| DELETE | `/api/parking-spots/{id}`                                        | Delete a parking spot           | `204 No Content` |
| PUT    | `/api/parking-spots/{parkingSpotId}/assign-zone/{parkingZoneId}` | Assign a spot to a parking zone | `200 OK`         |

### Create a Parking Spot

```http
POST /api/parking-spots
Content-Type: application/json
```

Example request:

```json
{
  "parkingZoneId": 1,
  "spotNumber": "A-001",
  "type": "STANDARD",
  "active": true
}
```

The accepted parking spot type values depend on the enum values defined in the project.

### Assign a Spot to a Zone

```http
PUT /api/parking-spots/5/assign-zone/1
```

This request assigns parking spot `5` to parking zone `1`.

---

## Parking Ticket API

Base path:

```text
/api/parking-tickets
```

| Method | Path                        | Description                | Success Status   |
| ------ | --------------------------- | -------------------------- | ---------------- |
| POST   | `/api/parking-tickets`      | Create a parking ticket    | `201 Created`    |
| GET    | `/api/parking-tickets`      | Get all parking tickets    | `200 OK`         |
| GET    | `/api/parking-tickets/{id}` | Get a parking ticket by ID | `200 OK`         |
| PUT    | `/api/parking-tickets/{id}` | Update a parking ticket    | `200 OK`         |
| DELETE | `/api/parking-tickets/{id}` | Delete a parking ticket    | `204 No Content` |

### Create a Parking Ticket

```http
POST /api/parking-tickets
Content-Type: application/json
```

The exact request fields are defined in:

```text
src/main/java/com/myexam/parkingsystem/dto/parking_ticket/ParkingTicketRequest.java
```

---

## Requirements

Check your Java version:

```bash
java --version
```

Check your Maven version:

```bash
mvn --version
```

## Clone the Repository

```bash
git clone https://github.com/shumkar-barpiev/swam-parking-system.git
cd swam-parking-system
```

## Application Profiles

The application uses Spring profiles.

The default profile is:

```properties
spring.profiles.default=dev
```

### Development Profile

The development profile uses H2.

Configuration file:

```text
src/main/resources/application-dev.properties
```

Run with the development profile:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

You can also select the profile explicitly:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production Profile

The production profile is intended to use PostgreSQL.

Configuration file:

```text
src/main/resources/application-prod.properties
```

Run with the production profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

You can also use an environment variable:

```bash
export SPRING_PROFILES_ACTIVE=prod
./mvnw spring-boot:run
```

Do not commit real production database passwords to the repository. Use environment variables for sensitive values.

Example:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

Run the JAR with a specific profile:

```bash
java -jar target/parking-management-system-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=prod
```

## Run Tests

Run all tests:

```bash
./mvnw clean test
```

Run verification tasks:

```bash
./mvnw clean verify
```

Run one test class:

```bash
./mvnw -Dtest=DriverControllerTest test
```

Run one test method:

```bash
./mvnw -Dtest=DriverControllerTest#shouldCreateDriver test
```

```bash
curl -X PUT \
  http://localhost:8080/api/drivers/1/vehicles/3
```

## Future Improvements

Possible future improvements include:

* Spring Security authentication and authorization
* OpenAPI and Swagger documentation
* Pagination and sorting
* Filtering and searching
* Docker support
* Database migration with Flyway or Liquibase
* More integration tests
* End-to-end tests
* Deployment configuration
* Improved logging and monitoring

## Author

Developed by [shumkar-barpiev](https://github.com/shumkar-barpiev).
