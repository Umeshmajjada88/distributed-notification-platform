############################################
# Build Stage
############################################

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /workspace

COPY pom.xml .
COPY shared/pom.xml shared/
COPY notification-service/pom.xml notification-service/
COPY delivery-service/pom.xml delivery-service/

RUN mvn dependency:go-offline -B

COPY shared shared
COPY notification-service notification-service
COPY delivery-service delivery-service

RUN mvn \
    -pl delivery-service \
    -am \
    clean package \
    -DskipTests

############################################
# Runtime Stage
############################################

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /workspace/delivery-service/target/*.jar \
    app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]