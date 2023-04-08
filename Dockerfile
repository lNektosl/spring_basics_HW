FROM maven:3.8.4-jdk-11-slim as build

COPY src/main/resources .

RUN mvn clean install

FROM tomcat:9.0 as production
COPY --from=build /target/demo-1.0-SNAPSHOT.war ./webapps
