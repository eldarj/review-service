#!/bin/bash

# Package a jar
./mvnw clean package -DskipTests

# Extract review-service version
POM_VERSION=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)

# Build the image providing the version
docker build \
  --build-arg JAR_FILE_PATH=target/review-service-"$POM_VERSION".jar \
  -t ejahijagic/review-service:"$POM_VERSION" \
  -t ejahijagic/review-service:latest \
  .

# Create network
docker network create challenge-network

# Stop if already running
docker-compose down -v

# Run a container
docker-compose up

