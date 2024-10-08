FROM maven:3.9.8-eclipse-temurin-21-alpine as builder
WORKDIR /app

COPY pom.xml .
COPY core/pom.xml ./core/
COPY application/pom.xml ./application/
COPY usecase/pom.xml ./usecase/
COPY infra/pom.xml ./infra/
COPY coverage/pom.xml ./coverage/

RUN mvn dependency:go-offline -B

COPY core/src ./core/src
COPY usecase/src ./usecase/src
COPY application/src ./application/src
COPY infra/src ./infra/src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine as prod

COPY --from=builder /app/infra/target/*.jar app.jar

# SERVER AND APPLICATION CONFIGURATIONS ARGS
ARG SPRING_PROFILES_ACTIVE
ARG PORT
ARG APP_VERSION
ARG APP_SERVER_URL
ARG APP_SECRET
ARG APP_ENVIRONMENT
# DATABASE CONFIGURATIONS ARGS
ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD
ARG SPRING_JPA_SHOW_SQL
# CONFIGURING CACHE
ARG SPRING_DATA_REDIS_HOST
ARG SPRING_DATA_REDIS_PORT
# SERVER AND APPLICATION CONFIGURATIONS
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENV PORT=${PORT}
ENV APP_VERSION=${APP_VERSION}
ENV APP_SERVER_URL=${APP_SERVER_URL}
ENV APP_SECRET=${APP_SECRET}
ENV APP_ENVIRONMENT=${APP_ENVIRONMENT}
# DATABASE CONFIGURATIONS
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV SPRING_JPA_SHOW_SQL=${SPRING_JPA_SHOW_SQL}
# CONFIGURING CACHE
ENV SPRING_DATA_REDIS_HOST=${SPRING_DATA_REDIS_HOST}
ENV SPRING_DATA_REDIS_PORT=${SPRING_DATA_REDIS_PORT}
# EXPOSE THE PORT
EXPOSE ${PORT}
# RUN THE APPLICATION
ENTRYPOINT ["java", "-jar", "/app.jar"]