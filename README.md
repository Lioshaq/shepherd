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
