# Authmn
Spring Boot application Kotlin based which interacts with Google OIDC service, through the code flow, for printing the
email address of the user that give his/her authorization to the app. 

# Basic usage

Before building and starting the application, fill the following properties in the [application.yaml](src/main/resources/application.yaml):
```
app:
  oauth:
    clientId: "CLIENT ID VALUE"
    secret: "CLIENT SECRETE VALUE"
```

Build and start the application, then:

- GET localhost:8080/authenticate/initializeFlow: This call will return the URL that is used for starting the whole
code flow.
- Navigate to the url previously obtained: the usual Google page asking if you are willing to give permission to the
application to read your personal data will appear.
- Complete the procedure, at which at the end of it, you'll be redirected again into the application where you'll be able 
to see the email address used for granting the authorization.

