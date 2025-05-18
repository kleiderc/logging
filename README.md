# structure
Spring Boot Folder Structure (Best Practices)

## Testing the CMD Repository of the Project

Execute the following steps to test on the CMD repository:

### 1. Retrieve all users


curl http://localhost:8080/users 

### 2. Insert an user

curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d "{\"name\":\"Alice\",\"email\":\"alice@example.com\"}"

### 2. Retrieve an user with id equal 2

curl http://localhost:8080/users/2

### 2. Update an user with id equal 2

curl -X PUT http://localhost:8080/users/2 -H "Content-Type: application/json" -d "{\"name\":\"Alice\",\"email\":\"alice.update@example.com\"}"

### 2. Delete an user with id equal 2

curl -X DELETE http://localhost:8080/users/2