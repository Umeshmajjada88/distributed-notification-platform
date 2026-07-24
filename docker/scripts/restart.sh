#!/bin/bash

echo "Restarting Platform..."

docker compose down

docker compose up --build -d