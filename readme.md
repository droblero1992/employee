# Employee Management Module

REST service for comprehensive employee management (paginated queries, single/batch creation, updates, and deletion), designed following Hexagonal Architecture principles.

**Base Path:** `/api/v1/employees`

---

## Table of Contents
- [Prerequisites & Getting Started](#prerequisites--getting-started)
- [Endpoints Summary](#endpoints-summary)
- [Queries (`GetEmployeeController`)](#queries-getemployeecontroller)
    - [1. List Employees (Paginated)](#1-list-employees-paginated)
    - [2. Get Employee by ID](#2-get-employee-by-id)
    - [3. Search Employees by Name (Paginated)](#3-search-employees-by-name-paginated)
- [Modifications (`ModifyEmployeeController`)](#modifications-modifyemployeecontroller)
    - [4. Create One or More Employees (Batch)](#4-create-one-or-more-employees-batch)
    - [5. Update Employee Data](#5-update-employee-data)
    - [6. Delete Employee by ID](#6-delete-employee-by-id)
- [Models and DTOs Structure](#models-and-dtos-structure)
    - [EmployeeDTO](#employeedto)
    - [CreateEmployeeReq](#createemployeereq)
    - [EmployeePageResult](#employeepageresultt)
    - [ErrorResponse](#errorresponse)

---

## Prerequisites & Getting Started

### Requirements
- **Java:** JDK 17
- **Build Tool:** Apache Maven 3.8+ (or Maven Wrapper included in the project)

### Running the Application with Maven

You can build and start the service directly from the command line using the Maven Wrapper:

```bash
mvn spring-boot:run
```

To package the application into an executable JAR file:

```bash
./mvnw clean package
java -jar target/*.jar
```

---


---

## Endpoints Summary

| Method | Endpoint | Controller | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/employees` | `GetEmployeeController` | Retrieves the employee catalog with pagination. |
| `GET` | `/api/v1/employees/{id}` | `GetEmployeeController` | Retrieves employee details by ID. |
| `GET` | `/api/v1/employees/search` | `GetEmployeeController` | Searches employees by name matching (paginated). |
| `POST` | `/api/v1/employees` | `ModifyEmployeeController` | Creates one or more employees in a single request. |
| `PUT` | `/api/v1/employees/{id}` | `ModifyEmployeeController` | Updates all or partial employee information. |
| `DELETE` | `/api/v1/employees/{id}` | `ModifyEmployeeController` | Permanently deletes an employee by ID. |

---

## Queries (`GetEmployeeController`)

### 1. List Employees (Paginated)

Retrieves a paginated list of employees registered in the system.

* **Method:** `GET`
* **Path:** `/api/v1/employees`

#### Query Parameters

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `page` | `Integer` | **Yes** | Zero-based page index (starts at `0`). | `0` |
| `size` | `Integer` | **Yes** | Number of records per page. | `10` |

#### Request Example

```bash
curl -X GET "http://localhost:8080/api/v1/employees?page=0&size=10" \
  -H "Accept: application/json"
```

#### HTTP Responses

* **`200 OK`**: Query completed successfully.
  ```json
  {
    "content": [
      {
        "id": "c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123",
        "firstName": "Carlos",
        "middleName": "Alberto",
        "lastName": "Pérez",
        "secondLastName": "Gómez",
        "age": 30,
        "gender": "MASCULINO",
        "birthDate": "1996-05-15",
        "position": "Software Engineer",
        "startDate": "2024-01-15",
        "status": true
      }
    ],
    "page": 0
  }
  ```
* **`400 Bad Request`**: Missing or invalid pagination parameters.
* **`500 Internal Server Error`**: Unhandled server error.

---

### 2. Get Employee by ID

Retrieves detailed information for a single employee using their unique identifier.

* **Method:** `GET`
* **Path:** `/api/v1/employees/{id}`

#### Path Variables

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `id` | `String` | **Yes** | Unique employee identifier. | `c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123` |

#### Request Example

```bash
curl -X GET "http://localhost:8080/api/v1/employees/c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123" \
  -H "Accept: application/json"
```

#### HTTP Responses

* **`200 OK`**: Employee found successfully.
  ```json
  {
    "id": "c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123",
    "firstName": "Carlos",
    "middleName": "Alberto",
    "lastName": "Pérez",
    "secondLastName": "Gómez",
    "age": 30,
    "gender": "MASCULINO",
    "birthDate": "1996-05-15",
    "position": "Software Engineer",
    "startDate": "2024-01-15",
    "status": true
  }
  ```
* **`400 Bad Request`**: Invalid ID format.
* **`404 Not Found`**: Employee does not exist.
  ```json
  {
    "status": 404,
    "error": "Not Found",
    "message": "Employee not found with ID: c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123",
    "timestamp": "2026-09-09 07:30:00"
  }
  ```
* **`500 Internal Server Error`**: Unhandled server error.

---

### 3. Search Employees by Name (Paginated)

Searches for employees matching a name pattern, returning paginated results.

* **Method:** `GET`
* **Path:** `/api/v1/employees/search`

#### Query Parameters

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `name` | `String` | **Yes** | Partial text to match against employee names. | `Carlos` |
| `page` | `Integer` | **Yes** | Zero-based page index (starts at `0`). | `0` |
| `size` | `Integer` | **Yes** | Number of records per page. | `10` |

#### Request Example

```bash
curl -X GET "http://localhost:8080/api/v1/employees/search?name=Carlos&page=0&size=10" \
  -H "Accept: application/json"
```

#### HTTP Responses

* **`200 OK`**: Matching employees retrieved successfully.
  ```json
  {
    "content": [
      {
        "id": "c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123",
        "firstName": "Carlos",
        "middleName": "Alberto",
        "lastName": "Pérez",
        "secondLastName": "Gómez",
        "age": 30,
        "gender": "MASCULINO",
        "birthDate": "1996-05-15",
        "position": "Software Engineer",
        "startDate": "2024-01-15",
        "status": true
      }
    ],
    "page": 0
  }
  ```
* **`400 Bad Request`**: Missing required parameters (`name`, `page`, or `size`).
* **`500 Internal Server Error`**: Unhandled server error.

---

## Modifications (`ModifyEmployeeController`)

### 4. Create One or More Employees (Batch)

Allows atomic registration of single or multiple employees in a single request.

* **Method:** `POST`
* **Path:** `/api/v1/employees`
* **Content-Type:** `application/json`

#### Payload Structure (`CreateEmployeeReq`)

| Field | Type | Required | Description |
| :--- | :--- | :--- | :--- |
| `employees` | `List<EmployeeDTO>` | **Yes** | Array of employee records to register. |

#### Request Payload Example

```json
{
  "employees": [
    {
      "firstName": "Carlos",
      "middleName": "Alberto",
      "lastName": "Pérez",
      "secondLastName": "Gómez",
      "age": 30,
      "gender": "MASCULINO",
      "birthDate": "1996-05-15",
      "position": "Software Engineer",
      "startDate": "2024-01-15",
      "status": true
    },
    {
      "firstName": "Ana",
      "middleName": "María",
      "lastName": "López",
      "secondLastName": "Hernández",
      "age": 28,
      "gender": "FEMENINO",
      "birthDate": "1997-11-22",
      "position": "QA Lead",
      "startDate": "2023-08-01",
      "status": true
    }
  ]
}
```

#### HTTP Responses

* **`204 No Content`**: Employees saved successfully. No content returned.
* **`400 Bad Request`**: Invalid data, missing required fields, age under 18, or incorrect date format.
* **`500 Internal Server Error`**: Internal error during insertion processing.

---

### 5. Update Employee Data

Updates general information for an existing employee identified by ID.

* **Method:** `PUT`
* **Path:** `/api/v1/employees/{id}`
* **Content-Type:** `application/json`

#### Path Variables

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `id` | `String` | **Yes** | Identifier of the employee to update. | `c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123` |

#### Request Payload Example

```json
{
  "firstName": "Carlos",
  "middleName": "Alberto",
  "lastName": "Pérez",
  "secondLastName": "Gómez",
  "age": 31,
  "gender": "MASCULINO",
  "birthDate": "1996-05-15",
  "position": "Senior Backend Developer",
  "startDate": "2024-01-15",
  "status": true
}
```

#### HTTP Responses

* **`204 No Content`**: Employee updated successfully.
* **`400 Bad Request`**: Malformed input parameters or validation constraints violated.
* **`404 Not Found`**: The specified employee does not exist.
  ```json
  {
    "status": 404,
    "error": "Not Found",
    "message": "Employee not found with ID: c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123",
    "timestamp": "2026-09-09 07:30:00"
  }
  ```
* **`500 Internal Server Error`**: Unhandled server error.

---

### 6. Delete Employee by ID

Permanently removes an employee record from the system.

* **Method:** `DELETE`
* **Path:** `/api/v1/employees/{id}`

#### Path Variables

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `id` | `String` | **Yes** | Unique identifier of the employee to delete. | `c3f1a6e2-5b8d-4f10-9b4e-28a49c95b123` |

#### HTTP Responses

* **`204 No Content`**: Employee deleted successfully.
* **`400 Bad Request`**: Invalid ID format.
* **`404 Not Found`**: Employee not found.
* **`500 Internal Server Error`**: Unhandled server error.

---

## Models and DTOs Structure

### `EmployeeDTO`

| Field | Type | Required | Format / Constraints | Description |
| :--- | :--- | :--- | :--- | :--- |
| `id` | `String` | No (on create) | UUID / Alphanumeric | Unique employee identifier. |
| `firstName` | `String` | **Yes** (`@NotNull`) | String | Employee's first name. |
| `middleName` | `String` | **Yes** (`@NotNull`) | String | Employee's middle name. |
| `lastName` | `String` | **Yes** (`@NotNull`) | String | Primary/paternal last name. |
| `secondLastName` | `String` | **Yes** (`@NotNull`) | String | Secondary/maternal last name. |
| `age` | `Integer` | **Yes** (`@NotNull`) | Min: `18` | Employee age in completed years. |
| `gender` | `String` | **Yes** (`@NotNull`) | String | Registered gender/sex. |
| `birthDate` | `LocalDate` | **Yes** (`@NotNull`) | `yyyy-MM-dd` | Date of birth. |
| `position` | `String` | **Yes** (`@NotNull`) | String | Job position / role in the organization. |
| `startDate` | `LocalDate` | No | `yyyy-MM-dd` | Employment start date. |
| `status` | `Boolean` | **Yes** (`@NotNull`) | `true` / `false` | Active/inactive employee state. |

### `CreateEmployeeReq`

| Field | Type | Required | Description |
| :--- | :--- | :--- | :--- |
| `employees` | `List<EmployeeDTO>` | **Yes** | Mandatory non-empty list of employee records to create. |

### `EmployeePageResult<T>`

| Field | Type | Description |
| :--- | :--- | :--- |
| `content` | `List<T>` | List of records contained within the current page. |
| `page` | `Integer` | Zero-based index of the returned page. |

### `ErrorResponse`

| Field | Type | Description |
| :--- | :--- | :--- |
| `status` | `Integer` | Returned HTTP status code (e.g., `400`, `404`, `500`). |
| `error` | `String` | Short descriptive HTTP reason phrase (e.g., `Not Found`). |
| `message` | `String` | Detailed cause of the error or failure description. |
| `timestamp` | `String` | Timestamp of the event (`yyyy-MM-dd HH:mm:ss`). |