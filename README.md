# Droplet-Server
This is the server for FitNet. It utilizes Spring Boot and PostgresSQL to provide a RESTful API for the FitNet IOS app.

## Getting Started
### Prerequisites
* Java 20
* Maven 3.6.3
* PostgresSQL 12.2

### Installing
1. Clone the repository
```bash
git clone
```
2. Build the project
```bash
mvn clean install
```
3. Run the project
```bash
mvn spring-boot:run
```
4. Open Postman and import the collection from the root directory
5. Run the collection
6. You should see a 200 response with a message "FitNet is running!"
7. You can now make requests to the API
