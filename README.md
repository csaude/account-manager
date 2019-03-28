# Account Manager
Account Manager is a Single Sign On (SSO) solution developed by the Dev team of FGH. All the aplication that for a reason os security will need the security aproach will be linked with this solution.

# JWT Authentication Configuration
The Account manager components authenticates users by issuing JWT tokes using HMAC algorithm which uses a secret key for encryption. By default the
the key `secret`, however one can declare a different key using the runtime properties file with default path `/opt/account_runtime.properties`. It is
also possible to use a different path by setting the environment variable `ACCOUNT_RUNTIME_PROPERTIES_PATH`. The key declaration in the file looks as
follows.

```$xslt
jwt.key=secret
```
* The application returns an HTTP 401 Unauthorized code when user attempts to authenticate with wrong credentials. With the valid credentials, and 
unlocked account, the application returns a response that resembles the following.

```json
{
    "email": "mimi@example.net",
    "fullName": "Stelio Moiane",
    "jwtToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZGEueWV1bmciLCJpc3MiOiJhY2NvdW50LW1hbmFnZXIiLCJpYXQiOjE1NTE0Njg1ODUsInV1aWQiOiI5MGM1MzEzYThkOGI0NDBmYTkwNTg3N2RhNDRlNjlmNiJ9.EpD0G-YPXo3-kNpWTtZNFAK5qDmwWbNHvF8nMT3CjL0",
    "username": "stelio.moiane",
    "uuid": "90c5313a8d8b440faxyz77da44e69f6"
}
```