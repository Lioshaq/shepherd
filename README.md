Create jar:
mvn clean install

Start:
cd target/
java -jar Shepherd-1.0-SNAPSHOT.jar

DB prop files:
target/config/


Requests:
Register
curl -i -H "Content-Type: application/json" -X POST -d '{"phone":"+37369111111","password":"password", "email":"test@gmail.com"}' http://localhost:8080/register
Get Token
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"test@gmail.com","password":"password"}' http://localhost:8080/auth

curl -i -H "Content-Type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4ZWkubWVsbmljQGdtYWlsLmNvbSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTQ1NzM4MjI0MzY2MywiZXhwIjoxNDU3NDgyMjQzfQ.R9kgt9H5Bfn9PGIvNwkOHo3UEDWwa4dYLq2v-ZrUGFRXtJJtcOzYRlGmqdoIjAoUzeWIFJkLJdhhutQ_Osg1jg" -X GET http://localhost:8080/account

