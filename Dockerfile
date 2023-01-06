# download java 11 image and gradle
FROM --platform=linux/amd64 maven:3.8.7 as build
WORKDIR /app

ARG POSTGRES_USER=hhuncvkl
ARG POSTGRES_PASSWORD=kuoAV6N9gSzw1w4YnwuT4BttwHR-k1xQ

COPY src src
COPY pom.xml pom.xml

RUN mvn package spring-boot:repackage -DskipTests

FROM --platform=linux/amd64 openjdk:17

COPY --from=build /app/target/*.jar campfire.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-DPOSTGRES_USER=hhuncvkl", "-DPOSTGRES_PASSWORD=kuoAV6N9gSzw1w4YnwuT4BttwHR-k1xQ", "-jar", "campfire.jar" ]
