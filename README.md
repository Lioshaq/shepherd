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

curl -i -H "Content-Type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4ZWkubWVsbmljQGdtYWlsLmNvbSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTQ1NzI1OTA2NjI5NywiZXhwIjoxNDU3MzU5MDY2fQ.JCF30UaiRX1YrjxH9gG-OAVnu8T5rOQDUb2K363QErT-hsQsSpaR_t0b2Lug22tUAr1s509NlnP4Ecg_ttxp2Q" -X GET http://localhost:8080/protected

