#!/bin/bash

echo "Cleaning Docker Environment..."

docker compose down -v

docker image prune -f

docker volume prune -f

docker network prune -f

echo "Cleanup Complete"