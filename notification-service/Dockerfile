# ===========================
# Build Stage
# ===========================

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml .
COPY shared/pom.xml shared/pom.xml
COPY notification-service/pom.xml notification-service/pom.xml

COPY shared/src shared/src
COPY notification-service/src notification-service/src

RUN mvn -pl shared,notification-service \
    -am \
    clean package \
    -DskipTests

# ===========================
# Runtime Stage
# ===========================

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /build/notification-service/target/*.jar \
    app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]