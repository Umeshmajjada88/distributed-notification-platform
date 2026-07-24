#!/bin/bash

echo "======================================"
echo "Starting Distributed Notification Platform"
echo "======================================"

docker compose up --build -d

echo ""
echo "Platform Started Successfully"
echo ""
echo "Notification Service : http://localhost:8080"
echo "Delivery Service     : http://localhost:8082"
echo "Kafka UI             : http://localhost:8085"