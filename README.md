# SecurityAppDTO_JWT
## Project Info
This is a tutorial project that was developed to learn the basics of Spring Security, Spring Boot, Rest API, and authentication using a JWT token.

In this REST API project written in JAVA SPRING FRAMEWORK, we test the logic of client authentication on our server using JSON Web Token (JWT), as opposed to authentication using a session and cookies.

This is applicable to a microservice architecture, where we do not store client data in a session on the server, but send it to the client as a JWT to avoid duplication of client data on each microservice, and the errors associated with it.
A PostgreSQL database is connected to the project, in which we store registered users, the POSTMAN program is used to make requests.

## Testing logic
### 1. Registration
To register a user from POSTMAN, we send a POST request with JSON in the request body to ***url***: http://localhost:8080/auth/registration.
In JSON, we have 3 fields: ***username, yearOfBirth, password***. The ***password*** field is encrypted in our project using the **HMAC256** encryptor, and is stored in the database in this form.

In the ***AuthController*** controller, in the ***performRegistration*** controller method, this request is executed, the DTO object is received from it, which is converted into a model (PersonDTO during registration, AuthenticationDTO during authentication).

Then in this method the received object is validated by the Spring validator (does the user with this name exist in the database, and also a simpler check of the correctness of filling in the fields: the fields in the incoming JSON should not be empty, from 2 to 30 characters, the year of birth should be valid).
If an error is detected, it sends back to the client a JSON with an error message.

If there is no error, the client is registered in the database and a generated JWT token is sent to it (displayed in the POSTMAN application console).

### 2. Checking

To check authentication using JWT, you need to make a GET request to the address: ***/showUserInfo***, while the token itself should be in the request headers under the Authorization key. If everything is correct, the client has authenticated using JWT, the program works successfully, the username that we included there when creating this token will be extracted from JWT and will be shown in the POSTMAN console, if not, there will be an error message.
JWT token has an expiration date of 60 minutes, after this period for authentication, you need to send a POST request again to ***/login*** specifying in JSON ***username*** and ***password*** and a new token will be generated.
### 3. Checking authorization
To check user authorization, there is a method ***adminPage*** in the HelloController class. Authorization is when the client has already logged in, but we want to know his access rights.

By default, clients receive the USER role upon registration, we can assign the ADMIN role to some user using a manual SQL query in the database. After receiving a JWT token, only a user with the ADMIN role can get into the ***adminPage*** method, make a GET request to ***/admin*** and get their ***username*** from the token.
## Information about some classes
### 1.JWTFilter
The JWTFilter class catches incoming JWT tokens in request headers by the ***Authorization*** key. If the header is present, it is not empty, and the value begins with the word ***Bearer***, then the token is checked; if this is the token we issued, validation is successful.
 #### Token structure:
1. Headers with service information
2. Payload - the token body, we have the username there, but there may be some information.
3. Secret - a special secret string,created on our server in a separate file and known only to us. The secret string does not allow the signature to be forged.


   All three of these data are hashed and then they are called a signature, the token contains this signature. When a token comes to us, we check its signature to see if it matches the one stored on our server. If it does, then this token was issued by us.
### 2.SecurityConfig
Configures SpringSecurity itself, authorization, sets up authentication.
### 3.AuthorizationDTO and PersonDTO
Implement interaction between the model and REST API services in the DTO format.
### 4. Model
Database connections, field validation.
### 5. Repositories and services
Implement interaction with the database, as in a standard CRUD application.
### 6.JWTUtil in the security package.
In this class, we have 2 methods: ***generateToken*** — generate the token itself, receiving ***username*** receiving input as a string.

The ***validateTokenAndRetriveClaim*** method checks the incoming token, which it also receives as a string, in case of successful validation it returns ***username***, which we put in the token, otherwise throw an exception with an errorл

**Project author: Vladimir Krizhanivsky - Backend-End Engineer**