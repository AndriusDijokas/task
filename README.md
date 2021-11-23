
To create image use **jibDockerBuild** with gradle you must have
docker installed locally. Image will be created you can run it with 8080 port
go to app localhost:8080/swagger-ui.html

To register user use:
curl -X POST "http://localhost:8080/auth/signup" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"test@test.lt\", \"name\": \"TestUser\", \"password\": \"TestUser\"}"
after that use login, and you will get your token.

To log in register via google oAuth02 use
http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:8080/oauth2/redirect/google

After login, you will be redirected to **redirect_uri** with path parameter token you can use
this token to do actions with API.
