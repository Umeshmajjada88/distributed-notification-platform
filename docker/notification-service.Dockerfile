############################################
# Build Stage
############################################

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /workspace

#
# Copy only pom files first
#
COPY pom.xml .
COPY shared/pom.xml shared/
COPY notification-service/pom.xml notification-service/
COPY delivery-service/pom.xml delivery-service/

#
# Download dependencies (cached layer)
#
RUN mvn dependency:go-offline -B

#
# Copy source
#
COPY shared shared
COPY notification-service notification-service
COPY delivery-service delivery-service

#
# Build only notification-service (+ dependencies)
#
RUN mvn \
    -pl notification-service \
    -am \
    clean package \
    -DskipTests

############################################
# Runtime Stage
############################################

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /workspace/notification-service/target/*.jar \
    app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]