#!/bin/bash

# Package a jar
./mvnw clean package -DskipTests

# Extract review-service version
POM_VERSION=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)

# Build the image and pass the jar file path with version
docker build \
  --build-arg JAR_FILE_PATH=target/review-service-"$POM_VERSION".jar \
  -t ejahijagic/review-service:"$POM_VERSION" \
  -t ejahijagic/review-service:latest \
  .

# Create a network to be shared between the service and mongo
docker network create challenge-network

# Stop if already running
docker-compose down -v

# Run
docker-compose up

