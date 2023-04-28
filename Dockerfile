FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY ./target/ldap-demo-exec.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "ldap-demo-exec.jar"]
