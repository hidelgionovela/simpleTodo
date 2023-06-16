# TO-DO App REST API - SpringBoot

This is a simple REST API built with SpringBoot framework for a TO-DO app. It provides endpoints to manage users and tasks associated with them.

## Endpoints

### User Controller

#### Get User by ID

```http
GET /user/{id}
```

This endpoint retrieves a specific user by their ID.

#### Update User

```http
PUT /user/{id}
```

This endpoint updates the information of a specific user identified by their ID.

#### Delete User

```http
DELETE /user/{id}
```

This endpoint deletes a specific user identified by their ID.

#### Get All Users

```http
GET /user
```

This endpoint retrieves all the users available in the system.

#### Create User

```http
POST /user
```

This endpoint creates a new user.

### Task Controller

#### Get Task by ID

```http
GET /task/{id}
```

This endpoint retrieves a specific task by its ID.

#### Update Task

```http
PUT /task/{id}
```

This endpoint updates the information of a specific task identified by its ID.

#### Delete Task

```http
DELETE /task/{id}
```

This endpoint deletes a specific task identified by its ID.

#### Create Task

```http
POST /task
```

This endpoint creates a new task.

#### Get Tasks by User ID

```http
GET /task/user/{user_id}
```

This endpoint retrieves all the tasks associated with a specific user identified by their ID.

## Setup and Running the Application

1. Clone the repository:

```bash
git clone https://github.com/hidelgionovela/simpleTodo.git
```

2. Navigate to the project directory:

```bash
cd simpleTodo
```

3. Build the application:

```bash
./mvnw clean package
```

4. Run the application:

```bash
./mvnw spring-boot:run
```

The application will start running on `http://localhost:8080`.

## Technologies Used

- SpringBoot
- Java
- RESTful API principles

## Requirements

- Java Development Kit (JDK) 17 
- Apache Maven
- SpringBoot

## License

This project is licensed under the [MIT License](LICENSE).
