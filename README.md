# URL Shortener

A simple URL shortening application built with **Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

## Features

* Shorten long URLs into unique short codes
* Check for existing URLs before creating a new short URL
* Handle short-code collisions
* Store URL mappings in MySQL
* Redirect short URLs to their original URLs

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* HTML/CSS

## How It Works

```text
Long URL
   ↓
Controller
   ↓
Service
   ↓
Check existing URL
   ↓
Generate unique short code
   ↓
MySQL
   ↓
Short URL
```

When a short URL is accessed, the application looks up its short code and redirects to the stored original URL.

## Setup

### 1. Create the database

```sql
CREATE DATABASE url_shortener;
```

### 2. Configure MySQL

Add your database credentials to `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 3. Run the application

Windows:

```bash
mvnw.cmd spring-boot:run
```

The application runs at:

```text
http://localhost:8080
```

## Future Improvements

* [ ] URL validation and global exception handling
* [ ] Unit and integration testing
* [ ] Swagger/OpenAPI documentation
* [ ] URL expiration
* [ ] Click analytics
* [ ] User authentication
* [ ] Redis caching
* [ ] Rate limiting
* [ ] Docker and Docker Compose
* [ ] GitHub Actions CI/CD
* [ ] Custom aliases and QR codes

## Author

**Divya Allam**
