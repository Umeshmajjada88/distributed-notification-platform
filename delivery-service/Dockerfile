# ===========================
# Build Stage
# ===========================

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml .
COPY shared/pom.xml shared/pom.xml
COPY delivery-service/pom.xml delivery-service/pom.xml

COPY shared/src shared/src
COPY delivery-service/src delivery-service/src

RUN mvn -pl shared,delivery-service \
    -am \
    clean package \
    -DskipTests

# ===========================
# Runtime Stage
# ===========================

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder \
    /build/delivery-service/target/*.jar \
    app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]