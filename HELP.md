# Spring Boot 3 + Spring Security 6: LDAP Authentication (Bind) with custom authorities

When you're using LDAP for authentication, but users authorities (roles, rights etc.) are stored elsewhere 
(e.g. you need to call external service to get it)

Run within container:
```
mvn clean install
docker build -t ldap-demo .
docker run -d -p 8081:8081 --rm ldap-demo
```
or local:
```
mvn clean install
mvn spring-boot:run
```
Then make some requests:
```
curl -u user:password http://localhost:8081/hello
curl -u user:password http://localhost:8081/user-details
curl -u admin:password http://localhost:8081/user-details
```
