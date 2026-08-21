# URL Shortener

A **deployed URL shortening web application** built with **Java 21, Spring Boot, Thymeleaf, Spring Data JPA, Hibernate, and MySQL**.

The application converts long URLs into short, unique links and redirects users to the original destination when a short URL is accessed. It includes duplicate URL detection, unique short-code generation, collision handling, and persistent storage.

The application is **Dockerized and deployed on Render**, with **Aiven MySQL** used as the cloud-hosted database.

## Live Demo

**Live Application:** https://urlshortener-16kl.onrender.com/

---

## Project Overview

URL Shortener is a server-side rendered web application designed to demonstrate backend development concepts using the Spring ecosystem.

The application follows a layered architecture where HTTP requests are handled by controllers, business logic is implemented in the service layer, and database operations are managed through Spring Data JPA repositories.

The user interface is rendered on the server using **Thymeleaf templates**, without a separate frontend framework or client-side JavaScript.

### Core Flow

```text
User enters Long URL
        ↓
Spring Boot Controller
        ↓
Service Layer
        ↓
Check Existing URL
        ↓
Generate Unique Short Code
        ↓
Check Collision
        ↓
Persist Mapping
        ↓
Return Short URL
        ↓
Thymeleaf View
```

When a user accesses the generated short URL:

```text
User Opens Short URL
        ↓
Spring Boot Controller
        ↓
Extract Short Code
        ↓
Search Database
        ↓
Find Original URL
        ↓
HTTP Redirect
        ↓
Original Destination
```

---

## Key Features

* Convert long URLs into short, shareable links
* Detect and reuse existing URL mappings
* Generate unique short codes
* Handle short-code collisions
* Persist URL mappings using MySQL
* Redirect short URLs to their original destinations
* Server-side rendering using Thymeleaf
* HTML-based user interface using Thymeleaf templates
* No client-side JavaScript
* Dockerized application
* Cloud deployment using Render
* Managed cloud database using Aiven MySQL

---

## Key Engineering Highlights

### Duplicate URL Detection

Before creating a new mapping, the application checks whether the submitted URL already exists in the database.

This avoids unnecessarily creating multiple short codes for the same destination.

### Short-Code Collision Handling

Generated short codes are checked against existing database records before being persisted.

If a generated code already exists, another code is generated to maintain uniqueness.

### Persistent Data Storage

URL mappings are persisted using:

* Spring Data JPA
* Hibernate
* MySQL

This allows mappings to remain available across application restarts and deployments.

### Layered Architecture

The application separates responsibilities into:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

This keeps request handling, business logic, and data-access logic separated and easier to maintain.

---

## Architecture

```text
                         ┌─────────────────┐
                         │      User       │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │   Controller    │
                         │   Spring Boot   │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │     Service     │
                         │ Business Logic  │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │   Repository    │
                         │ Spring Data JPA │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │   Aiven MySQL   │
                         │    Database     │
                         └─────────────────┘
                                  │
                                  │
                         Retrieved Data
                                  │
                                  ▼
                         ┌─────────────────┐
                         │    Thymeleaf    │
                         │    Templates    │
                         └────────┬────────┘
                                  │
                                  ▼
                                User

```


## Deployment Architecture
```text

                         Spring Boot App
                                │
                                ▼
                             Docker
                                │
                                ▼
                             Render
                                │
                                ▼
                          Aiven MySQL

```

---

## Tech Stack

| Category              | Technology       |
| --------------------- | ---------------- |
| Language              | Java 21          |
| Backend               | Spring Boot      |
| Server-Side Rendering | Thymeleaf        |
| Persistence           | Spring Data JPA  |
| ORM                   | Hibernate        |
| Database              | MySQL            |
| Database Hosting      | Aiven            |
| Build Tool            | Maven            |
| Containerization      | Docker           |
| Deployment            | Render           |
| UI                    | Thymeleaf + HTML |


---

## Docker

The application is containerized using Docker so that the application and its runtime environment can be packaged consistently for deployment.

### Build the Docker Image

```bash
docker build -t url-shortener .
```

### Run the Container

```bash
docker run -p 8080:8080 url-shortener
```

The application will be available at:

```text
http://localhost:8080
```

---

## Project Structure

```text
url-shortener/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── codewithdivya/
│       │           ├── controller/
│       │           ├── service/
│       │           ├── repository/
│       │           ├── entity/
│       │           └── Application.java
│       │
│       └── resources/
│           ├── templates/
│           │   └── *.html
│           └── application.properties
│
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/divyaallam191-arch/URLShortener.git
cd URLShortener
```

### 2. Create the MySQL Database

```sql
CREATE DATABASE url_shortener;
```

### 3. Configure Database Connection

Update `application.properties` with your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
```

### 4. Run the Application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or on Linux/macOS:

```bash
./mvnw spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

> **Security:** Never commit real database credentials, API keys, passwords, or other secrets to the repository.

---

## Deployment

The application is deployed using the following architecture:

| Component        | Technology                  | Purpose                             |
| ---------------- | --------------------------- | ----------------------------------- |
| Application      | Spring Boot                 | Backend and business logic          |
| UI               | Thymeleaf                   | Server-side rendered user interface |
| Persistence      | Spring Data JPA + Hibernate | Database interaction                |
| Containerization | Docker                      | Application packaging               |
| Hosting          | Render                      | Hosts the deployed application      |
| Database         | Aiven MySQL                 | Cloud-hosted persistent storage     |

---

## What I Learned

Building and deploying this project provided hands-on experience with:

* Developing backend functionality using Spring Boot
* Designing layered backend applications
* Working with Spring Data JPA and Hibernate
* Connecting Spring Boot applications to MySQL
* Handling database persistence and uniqueness constraints
* Implementing URL redirection
* Containerizing Java applications with Docker
* Configuring applications for cloud deployment
* Connecting a deployed application to a managed cloud database
* Debugging deployment and database connectivity issues

---

## Future Improvements

Potential improvements include:

* URL validation
* Global exception handling
* Unit and integration testing
* URL expiration
* Click analytics
* User authentication
* Custom URL aliases
* Redis caching
* Rate limiting
* Docker Compose
* GitHub Actions CI/CD
* QR code generation

---

## Author

**Divya Allam**

Aspiring Software Engineer focused on **Java, Spring Boot, Backend Development, and Data Structures & Algorithms**.

If you found the project interesting, feel free to explore the repository.



