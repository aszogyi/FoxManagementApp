# Fox Management Application

Java EE application for managing foxes with REST API, JSF UI, scheduled background jobs and H2 persistence.

## Technology Stack

* Java 17
* Java EE 8
* EJB 3.2
* JSF 2.3
* JPA / Hibernate
* H2 Database
* WildFly 26.1.3
* Gradle
* JUnit 5
* Mockito

---

## Features

### Web UI (JSF + PrimeFaces)

* Create fox
* List foxes
* Delete fox
* Display fox images
* Sorting support
* Filtering support
* Pagination

---

### REST API

| Method | Endpoint          | Description        |
| ------ | ----------------- | ------------------ |
| GET    | `/api/foxes`      | Retrieve all foxes |
| GET    | `/api/foxes/{id}` | Retrieve fox by id |
| POST   | `/api/foxes`      | Create fox         |
| DELETE | `/api/foxes/{id}` | Delete fox         |
| DELETE | `/api/foxes/all`  | Delete all foxes   |

---

## Validation

Bean Validation (JSR-380):

* Name must not be blank
* Species must not be blank
* Gender must not be null

Error handling:

* 400 Bad Request
* 404 Not Found

Implemented using JAX-RS ExceptionMapper.

---

## Scheduled Job

Every 30 seconds:

* fetches one fox from the database where image is empty
* retrieves a random fox image from Random Fox API
* updates and persists the fox

Source:

https://randomfox.ca/

---

## Business Logic

Implemented using:

* EJB 3.2 Stateless Session Beans
* Declarative transaction management
  (`@TransactionAttribute(REQUIRED)`)

---

## Running the application

Build:

```bash
gradlew clean war
```

Deploy generated WAR:

```text
build/libs/FoxManagementApp-1.0.0.war
```

Copy to:

```text
wildfly/standalone/deployments/
```

Open:

```text
http://localhost:8080/FoxManagementApp-1.0.0/index.xhtml
```

---

## Testing

Run:

```bash
gradlew clean test
```

Implemented:

* Unit tests
* JUnit 5
* Mockito

Integration testing support can be extended with Arquillian.

---

## Docker

Build:

```bash
docker build -t fox-management .
```

Run:

```bash
docker run -p 8080:8080 fox-management
```

---

## Branching Strategy

* main → PROD
* develop → DEV
* release/* → UAT
* feature/* → feature development

---

## Notes

H2 database is used for local development and testing instead of Oracle Database.
